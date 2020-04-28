package com.ytz.shop.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ytz.shop.common.CommonResult;
import com.ytz.shop.pojo.Goods;
import com.ytz.shop.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: GoodsController
 * @Description: 商品Controller
 * @author: yangtianzeng
 * @date: 2020/4/28 14:47
 */
@Api(tags = "GoodsController", description = "商品Controller")
@RestController
@RequestMapping("rest/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("添加商品")
    @PostMapping("")
    public CommonResult<Goods> add(@RequestBody Goods goods) {
        Goods goods1 = goodsService.add(goods);
        if (ObjectUtil.isNotEmpty(goods1)) {
            return CommonResult.success(goods1);
        }
        return CommonResult.failed("添加失败");
    }

    @ApiOperation("更新商品信息")
    @PutMapping("{id}")
    public CommonResult<Integer> modify(@PathVariable Long id, @RequestBody Goods goods) {
        int result = goodsService.modify(id, goods);
        if (result > 0) {
            return CommonResult.success(result);
        }
        return CommonResult.failed("更新失败");
    }

    @ApiOperation("逻辑删除商品")
    @PutMapping("{id}/remove")
    public CommonResult<Integer> remove(@PathVariable Long id) {
        int result = goodsService.remove(id);
        if (result > 0) {
            return CommonResult.success(result);
        }
        return CommonResult.failed("删除失败");
    }

    @ApiOperation("查看商品详情")
    @GetMapping("{id}")
    public CommonResult<Goods> detail(@PathVariable Long id) {
        Goods goods = goodsService.getOne(id);
        if (ObjectUtil.isNotEmpty(goods)) {
            return CommonResult.success(goods);
        }
        return CommonResult.failed("查询商品详情失败");
    }

    @ApiOperation("分页列表")
    @GetMapping("")
    public CommonResult<Page<Goods>> detail(Integer pageNumber, Integer pageSize, String query) {
        Page<Goods> list = goodsService.list(pageNumber, pageSize, query);
        if (CollUtil.isNotEmpty(list)) {
            return CommonResult.success(list);
        }
        return CommonResult.failed("查询失败");
    }

}
