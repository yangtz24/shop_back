package com.ytz.shop.controller;

import cn.hutool.core.collection.CollUtil;
import com.ytz.shop.common.CommonResult;
import com.ytz.shop.pojo.Order;
import com.ytz.shop.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: OrderController
 * @Description: 订单Controller
 * @author: yangtianzeng
 * @date: 2020/5/2 10:54
 */
@Api(tags = "OrderController", description = "订单Controller")
@RestController
@RequestMapping("rest/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("分页查询")
    @GetMapping("")
    public CommonResult<Page<Order>> list(@RequestParam(defaultValue = "1") @ApiParam("当前页") Integer pageNumber,@RequestParam(defaultValue = "10") @ApiParam("每页显示数量") Integer pageSize, @ApiParam("查询参数")String query) {
        Page<Order> list = orderService.list(pageNumber, pageSize, query);
        if (CollUtil.isNotEmpty(list)) {
            return CommonResult.success(list);
        }
        return CommonResult.failed("查询失败");
    }
}
