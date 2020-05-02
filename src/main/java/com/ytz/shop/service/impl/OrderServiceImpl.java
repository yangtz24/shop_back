package com.ytz.shop.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ytz.shop.pojo.Order;
import com.ytz.shop.repository.OrderRepository;
import com.ytz.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: OrderServiceImpl
 * @Description: 订单业务实现类
 * @author: yangtianzeng
 * @date: 2020/5/2 10:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<Order> list(Integer pageNumber, Integer pageSize, String key) {

        if (pageNumber != null && pageNumber != 0) {
            pageNumber = pageNumber - 1;
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Specification<Order> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StrUtil.isNotEmpty(key)) {
                Predicate p = criteriaBuilder.like(root.get("order_number"), key + "%");
                list.add(p);
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        Page<Order> orders = orderRepository.findAll(specification, pageable);
        return orders;
    }
}
