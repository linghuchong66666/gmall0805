package com.atguigu.gmall.pms.service;

import com.atguigu.gmall.pms.vo.AttrVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.pms.entity.AttrEntity;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * 商品属性
 *
 * @author linghuchong
 * @email lxf@atguigu.com
 * @date 2020-01-02 18:53:15
 */
public interface AttrService extends IService<AttrEntity> {

    PageVo queryPage(QueryCondition params);

    PageVo queryByIdTypePage(QueryCondition queryCondition, Long cid, Integer type);

    void saveAttrVo(AttrVO attrVO);
}

