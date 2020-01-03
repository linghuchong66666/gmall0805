package com.atguigu.gmall.wms.dao;

import com.atguigu.gmall.wms.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author linghuchong
 * @email lxf@atguigu.com
 * @date 2020-01-03 08:49:38
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
