package com.atguigu.gmall.pms.dao;

import com.atguigu.gmall.pms.entity.FeightTemplateEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 运费模板
 *
 * @author linghuchong
 * @email lxf@atguigu.com
 * @date 2020-01-02 21:51:22
 */
@Mapper
public interface FeightTemplateDao extends BaseMapper<FeightTemplateEntity> { }