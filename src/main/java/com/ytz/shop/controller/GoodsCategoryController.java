package com.ytz.shop.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ytz.shop.common.CommonResult;
import com.ytz.shop.pojo.GoodsCategory;
import com.ytz.shop.service.GoodsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @ApiOperation("级联查询")
    @GetMapping("parent")
    public CommonResult<List<GoodsCategory>> cascadeList() {
        List<GoodsCategory> list = categoryService.cascadeList();
        if (CollUtil.isNotEmpty(list)) {
            return CommonResult.success(list);
        }
        return CommonResult.failed("查询失败");
    }

    @ApiOperation("删除")
    @DeleteMapping("{id}")
    public CommonResult remove(@PathVariable Long id) {
        categoryService.remove(id);
        return CommonResult.success("200", "删除成功");
    }

    @ApiOperation("添加分类")
    @PostMapping("")
    public CommonResult<GoodsCategory> add(@RequestBody GoodsCategory category) {
        GoodsCategory goodsCategory = categoryService.add(category);
        if (ObjectUtil.isNotEmpty(goodsCategory)) {
            return CommonResult.success(goodsCategory);
        }
        return CommonResult.failed("添加失败");
    }

    @ApiOperation("编辑")
    @PutMapping("{id}")
    public CommonResult<GoodsCategory> edit(@PathVariable Long id, @RequestBody GoodsCategory goodsCategory) {
        GoodsCategory category = categoryService.edit(id, goodsCategory);
        if (ObjectUtil.isNotEmpty(category)) {
            return CommonResult.success(category);
        }
        return CommonResult.failed("修改操作失败");
    }

    @ApiOperation("查询详情")
    @GetMapping("{id}")
    public CommonResult<GoodsCategory> detail(@PathVariable Long id) {
        GoodsCategory category = categoryService.detail(id);
        if (ObjectUtil.isNotEmpty(category)) {
            return CommonResult.success(category);
        }
        return CommonResult.failed("查询详情失败");
    }

    @ApiOperation("获取全部")
    @GetMapping("all")
    public CommonResult<List<GoodsCategory>> all() {
        List<GoodsCategory> categories =  categoryService.all();
        if (CollUtil.isNotEmpty(categories)) {
            return CommonResult.success(categories);
        }
        return CommonResult.failed("查询失败");
    }

}


