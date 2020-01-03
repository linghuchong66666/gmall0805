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

import com.atguigu.gmall.pms.entity.FeightTemplateEntity;
import com.atguigu.gmall.pms.service.FeightTemplateService;




/**
 * 运费模板
 *
 * @author linghuchong
 * @email lxf@atguigu.com
 * @date 2020-01-02 21:51:22
 */
@Api(tags = "运费模板 管理")
@RestController
@RequestMapping("pms/feighttemplate")
public class FeightTemplateController {
    @Autowired()
    private FeightTemplateService feightTemplateService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pms:feighttemplate:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = feightTemplateService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('pms:feighttemplate:info')")
    public Resp<FeightTemplateEntity> info(@PathVariable("id") Long id){
		FeightTemplateEntity feightTemplate = feightTemplateService.getById(id);

        return Resp.ok(feightTemplate);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('pms:feighttemplate:save')")
    public Resp<Object> save(@RequestBody FeightTemplateEntity feightTemplate){
		feightTemplateService.save(feightTemplate);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('pms:feighttemplate:update')")
    public Resp<Object> update(@RequestBody FeightTemplateEntity feightTemplate){
		feightTemplateService.updateById(feightTemplate);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('pms:feighttemplate:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		feightTemplateService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
