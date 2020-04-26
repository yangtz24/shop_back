package com.ytz.shop.repository;

import com.ytz.shop.common.enums.AttributeType;
import com.ytz.shop.pojo.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: AttributeRepository
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/22 17:47
 */
@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    /**
     * 据分类ID 参数类型   查询参数信息
     * @param cateId  分类ID
     * @param type  参数类型
     * @return
     */
    List<Attribute> findByCateIdAndType(Long cateId, AttributeType type);
}
