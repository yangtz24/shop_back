package com.ytz.shop.service;

import com.ytz.shop.pojo.Order;
import org.springframework.data.domain.Page;

/**
 * @ClassName: OrderService
 * @Description: 订单业务接口
 * @author: yangtianzeng
 * @date: 2020/5/2 10:46
 */
public interface OrderService {

    /**
     * 分页查询
     * @param pageNumber
     * @param pageSize
     * @param key
     * @return
     */
    Page<Order> list(Integer pageNumber, Integer pageSize, String key);
}
