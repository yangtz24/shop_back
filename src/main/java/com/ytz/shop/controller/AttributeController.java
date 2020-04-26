package com.ytz.shop.controller;

import cn.hutool.core.collection.CollUtil;
import com.ytz.shop.common.CommonResult;
import com.ytz.shop.pojo.Attribute;
import com.ytz.shop.service.AttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("{id}/attributes")
    public CommonResult<List<Attribute>> getAttributeByCateId(@PathVariable("id") Long cateId, String type) {
        List<Attribute> attributes = attributeService.getByCategoryId(cateId, type);
        if (CollUtil.isNotEmpty(attributes)) {
            return CommonResult.success(attributes);
        }
        return CommonResult.failed("查询失败");
    }
}
