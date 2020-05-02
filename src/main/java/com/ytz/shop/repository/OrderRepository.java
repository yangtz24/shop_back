package com.ytz.shop.repository;

import com.ytz.shop.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: OrderRepository
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/5/2 10:45
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
}
