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

import com.atguigu.gmall.pms.entity.WareOrderTaskEntity;
import com.atguigu.gmall.pms.service.WareOrderTaskService;




/**
 * 库存工作单
 *
 * @author linghuchong
 * @email lxf@atguigu.com
 * @date 2020-01-02 21:51:22
 */
@Api(tags = "库存工作单 管理")
@RestController
@RequestMapping("pms/wareordertask")
public class WareOrderTaskController {
    @Autowired
    private WareOrderTaskService wareOrderTaskService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pms:wareordertask:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = wareOrderTaskService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('pms:wareordertask:info')")
    public Resp<WareOrderTaskEntity> info(@PathVariable("id") Long id){
		WareOrderTaskEntity wareOrderTask = wareOrderTaskService.getById(id);

        return Resp.ok(wareOrderTask);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('pms:wareordertask:save')")
    public Resp<Object> save(@RequestBody WareOrderTaskEntity wareOrderTask){
		wareOrderTaskService.save(wareOrderTask);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('pms:wareordertask:update')")
    public Resp<Object> update(@RequestBody WareOrderTaskEntity wareOrderTask){
		wareOrderTaskService.updateById(wareOrderTask);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('pms:wareordertask:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		wareOrderTaskService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
