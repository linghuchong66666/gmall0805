package com.atguigu.gmall.pms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.Query;
import com.atguigu.core.bean.QueryCondition;

import com.atguigu.gmall.pms.dao.ShAreaDao;
import com.atguigu.gmall.pms.entity.ShAreaEntity;
import com.atguigu.gmall.pms.service.ShAreaService;


@Service("shAreaService")
public class ShAreaServiceImpl extends ServiceImpl<ShAreaDao, ShAreaEntity> implements ShAreaService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<ShAreaEntity> page = this.page(
                new Query<ShAreaEntity>().getPage(params),
                new QueryWrapper<ShAreaEntity>()
        );

        return new PageVo(page);
    }

}