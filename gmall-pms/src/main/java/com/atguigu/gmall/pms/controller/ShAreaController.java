package com.atguigu.gmall.pms.controller;

import java.util.Arrays;
import java.util.Map;


import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;
import com.atguigu.core.bean.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gmall.pms.entity.ShAreaEntity;
import com.atguigu.gmall.pms.service.ShAreaService;




/**
 * 全国省市区信息
 *
 * @author linghuchong
 * @email lxf@atguigu.com
 * @date 2020-01-02 21:51:22
 */
@Api(tags = "全国省市区信息 管理")
@RestController
@RequestMapping("pms/sharea")
public class ShAreaController {
    @Autowired
    private ShAreaService shAreaService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pms:sharea:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = shAreaService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('pms:sharea:info')")
    public Resp<ShAreaEntity> info(@PathVariable("id") Integer id){
		ShAreaEntity shArea = shAreaService.getById(id);

        return Resp.ok(shArea);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('pms:sharea:save')")
    public Resp<Object> save(@RequestBody ShAreaEntity shArea){
		shAreaService.save(shArea);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('pms:sharea:update')")
    public Resp<Object> update(@RequestBody ShAreaEntity shArea){
		shAreaService.updateById(shArea);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('pms:sharea:delete')")
    public Resp<Object> delete(@RequestBody Integer[] ids){
		shAreaService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
