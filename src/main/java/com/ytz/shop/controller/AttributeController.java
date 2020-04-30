package com.ytz.shop.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ytz.shop.common.CommonResult;
import com.ytz.shop.pojo.Attribute;
import com.ytz.shop.service.AttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: AttributeController
 * @Description: 分类参数Controller
 * @author: yangtianzeng
 * @date: 2020/4/23 17:04
 */
@Api(tags = "AttributeController", description = "分类参数Controller")
@RestController
@RequestMapping("rest/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @ApiOperation("根据分类ID查询参数信息")
    @GetMapping("{cateId}/attributes")
    public CommonResult<List<Attribute>> getAttributeByCateId(@PathVariable("cateId") @ApiParam("分类ID") Long cateId, String type) {
        List<Attribute> attributes = attributeService.getByCategoryId(cateId, type);
        if (CollUtil.isNotEmpty(attributes)) {
            return CommonResult.success(attributes);
        }
        return CommonResult.failed("查询失败");
    }

    @ApiOperation("添加分类参数")
    @PostMapping("{cateId}")
    public CommonResult<Attribute> add(@PathVariable("cateId") @ApiParam("分类ID") Long cateId, @RequestBody Attribute attr) {
        Attribute attribute = attributeService.add(cateId, attr);
        if (ObjectUtil.isNotEmpty(attribute)) {
            return CommonResult.success(attribute);
        }
        return CommonResult.failed("添加失败");
    }

    @ApiOperation("编辑分类参数")
    @PutMapping("{cateId}/{id}")
    public CommonResult<Integer> edit(@PathVariable("cateId") @ApiParam("分类ID") Long cateId, @PathVariable("id") @ApiParam("属性ID") Long id, @RequestBody Attribute attr) {
        Integer result = attributeService.edit(cateId, id, attr);
        if (result > 0) {
            return CommonResult.success(result);
        }
        return CommonResult.failed("更新失败");
    }

    @ApiOperation("获取参数详情")
    @GetMapping("{cateId}/{id}")
    public CommonResult<Attribute> detail(@PathVariable("cateId") @ApiParam("分类ID") Long cateId, @PathVariable("id") @ApiParam("属性ID") Long id, @ApiParam("属性类型，可取值：only,many")String type) {
        Attribute attribute = attributeService.detail(cateId, id, type);
        if (ObjectUtil.isNotEmpty(attribute)) {
            return CommonResult.success(attribute);
        }
        return CommonResult.failed("查询详情失败");
    }

    @ApiOperation("逻辑删除属性信息")
    @DeleteMapping("{cateId}/{id}")
    public CommonResult<Integer> remove(@PathVariable("cateId") @ApiParam("分类ID") Long cateId, @PathVariable("id") @ApiParam("属性ID") Long id) {
        Integer result = attributeService.remove(cateId, id);
        if (result > 0) {
            return CommonResult.success(result);
        }
        return CommonResult.failed("删除失败");
    }
}
