package com.ytz.shop.repository;

import com.ytz.shop.pojo.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: AttributeRepository
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/22 17:47
 */
@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
