package com.ytz.shop.controller;

import cn.hutool.core.collection.CollUtil;
import com.ytz.shop.common.CommonResult;
import com.ytz.shop.pojo.GoodsCategory;
import com.ytz.shop.service.GoodsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: GoodsCategoryController
 * @Description: 商品分类 控制层
 * @author: yangtianzeng
 * @date: 2020/4/17 18:02
 */
@Api(tags = "GoodsCategoryController", description = "商品分类Controller")
@RestController
@RequestMapping("rest/goods/category")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryService categoryService;

    @ApiOperation("分页查询")
    @GetMapping("")
    public CommonResult<Page<GoodsCategory>> list(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize, String key) {
        Page<GoodsCategory> categories = categoryService.list(currentPage, pageSize, key);
        if (CollUtil.isNotEmpty(categories)) {
            return CommonResult.success(categories);
        }
        return CommonResult.failed("查询失败");
    }
}
