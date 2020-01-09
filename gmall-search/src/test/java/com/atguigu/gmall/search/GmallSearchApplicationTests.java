package com.atguigu.gmall.search;

import com.atguigu.core.bean.QueryCondition;
import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.pms.entity.*;
import com.atguigu.gmall.search.feign.GmallPmsClient;
import com.atguigu.gmall.search.feign.GmallWmsClient;
import com.atguigu.gmall.search.pojo.Goods;
import com.atguigu.gmall.search.pojo.SearchAttrValue;
import com.atguigu.gmall.search.repository.GoodsRepository;
import com.atguigu.gmall.wms.entity.WareSkuEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallSearchApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Autowired
    private GoodsRepository repository;

    @Autowired
    private GmallPmsClient pmsClient;

    @Autowired
    private GmallWmsClient wmsClient;

    @Test
    public void contextLoads() {
        restTemplate.createIndex(Goods.class);
        restTemplate.putMapping(Goods.class);
    }

    @Test
    public void importData(){

        Long pageNumber = 1l;
        Long pageSize = 100l;

        do {
            QueryCondition queryCondition = new QueryCondition();
            queryCondition.setPage(pageNumber);
            queryCondition.setLimit(pageSize);
            //分页查询spu
            Resp<List<SpuInfoEntity>> listResp = pmsClient.querySpuByPage(queryCondition);
            List<SpuInfoEntity> spuInfoEntities = listResp.getData();
            //判断spu是否为空
            if (CollectionUtils.isEmpty(spuInfoEntities)){
                return;
            }
            //遍历spu，获取sku的es
            spuInfoEntities.forEach(spuInfoEntity -> {
                Resp<List<SkuInfoEntity>> skuResp = pmsClient.querySkuBySpuId(spuInfoEntity.getId());
                List<SkuInfoEntity> skuInfoEntities = skuResp.getData();
                if (!CollectionUtils.isEmpty(skuInfoEntities)){
                    List<Goods> goodsList = skuInfoEntities.stream().map(skuInfoEntity -> {
                        Goods goods = new Goods();
                        Resp<List<WareSkuEntity>> wareSkuResp = wmsClient.queryWareSkuBySkuId(skuInfoEntity.getSkuId());
                        List<WareSkuEntity> wareSkuEntities = wareSkuResp.getData();
                        if (!CollectionUtils.isEmpty(wareSkuEntities)){
                            goods.setStore(wareSkuEntities.stream().anyMatch(wareSkuEntity -> wareSkuEntity.getStock() > 0));
                        }
                        Resp<List<ProductAttrValueEntity>> searchAttrValue = pmsClient.querySearchAttrValue(skuInfoEntity.getSpuId());
                        List<ProductAttrValueEntity> attrValueEntities = searchAttrValue.getData();
                        List<SearchAttrValue> searchAttrValueEntities = attrValueEntities.stream().map(attrValueEntity -> {
                            SearchAttrValue attrValue = new SearchAttrValue();
                            attrValue.setAttrId(attrValueEntity.getAttrId());
                            attrValue.setAttrName(attrValueEntity.getAttrName());
                            attrValue.setAttrValue(attrValueEntity.getAttrValue());
                            return attrValue;
                        }).collect(Collectors.toList());
                        goods.setAttr(searchAttrValueEntities);
                        goods.setBrandId(skuInfoEntity.getBrandId());
                        Resp<BrandEntity> brandEntityResp = pmsClient.queryBrandById(skuInfoEntity.getBrandId());
                        BrandEntity brandEntity = brandEntityResp.getData();
                        if (brandEntity != null) {
                            goods.setBrandName(brandEntity.getName());
                        }
                        goods.setCategoryId(skuInfoEntity.getCatalogId());
                        Resp<CategoryEntity> categoryEntityResp = pmsClient.queryCategoryById(skuInfoEntity.getCatalogId());
                        CategoryEntity categoryEntity = categoryEntityResp.getData();
                        if (categoryEntity != null) {
                            goods.setCategoryName(categoryEntity.getName());
                        }
                        goods.setCreateTime(spuInfoEntity.getCreateTime());
                        goods.setDefaultImage(skuInfoEntity.getSkuDefaultImg());
                        goods.setPrice(skuInfoEntity.getPrice().doubleValue());
                        goods.setSale(10l);
                        goods.setSkuId(skuInfoEntity.getSkuId());
                        goods.setSkuSubTitle(skuInfoEntity.getSkuTitle());
                        goods.setSkuTitle(skuInfoEntity.getSkuSubtitle());
                        return goods;
                    }).collect(Collectors.toList());
                    repository.saveAll(goodsList);
                }
            });
            pageSize = (long)spuInfoEntities.size();
            pageNumber++;
        }while (pageSize == 100);

    }

}
