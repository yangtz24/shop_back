/*
package com.ytz.shop.es.controller;

import cn.hutool.core.util.ObjectUtil;
import com.ytz.shop.common.CommonResult;
import com.ytz.shop.es.document.EsGoods;
import com.ytz.shop.es.service.EsGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @ClassName: EsGoodsController
 * @Description: TODO
 * @author: basketBoy
 * @date: 2020/6/7
 * @Version: V1.0
 *//*

@Api(tags = "EsGoodsController", description = "搜索商品Controller")
@RestController
@RequestMapping("rest/es/goods")
public class EsGoodsController {

    @Autowired
    private EsGoodsService esGoodsService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @PostMapping("")
    public CommonResult<Integer> importAll() {
        int result = esGoodsService.importAll();
        if (result == 1) {
            return CommonResult.success(result);
        }
        return CommonResult.failed("导入数据失败");
    }

    @ApiOperation(value = "根据id删除商品")
    @PostMapping("remove/{id}")
    public CommonResult<Object> remove(@PathVariable Long id) {
        esGoodsService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id删除商品")
    @PostMapping("remove/batch")
    public CommonResult<Object> batchRemove(@RequestParam("ids") List<Long> ids) {
        esGoodsService.batchDelete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @PostMapping("create/{id}")
    public CommonResult<EsGoods> create(@PathVariable Long id) {
        EsGoods esProduct = esGoodsService.create(id);
        if(ObjectUtil.isNotEmpty(esProduct)) {
            return CommonResult.success(esProduct);
        }
        return CommonResult.failed("没有创建成功");
    }

    @ApiOperation(value = "搜索")
    @GetMapping("search")
    public CommonResult<Page<EsGoods>> search(@RequestParam(required = false) String keyword,
                                                                            @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                                            @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsGoods> esGoodsPage = esGoodsService.search(keyword, pageNum, pageSize);
        if(ObjectUtil.isNotEmpty(esGoodsPage)) {
            List<EsGoods> esGoodsList = new ArrayList<>();
            esGoodsPage.stream().forEach(product -> {
                EsGoods esProduct = new EsGoods();
                BeanUtils.copyProperties(product, esProduct);
                esGoodsList.add(esProduct);
            });
            return CommonResult.success(esGoodsPage);
        }
        return CommonResult.failed("没有数据，搜索出错");
    }
}
*/
