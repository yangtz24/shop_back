package com.ytz.shop.repository;

import com.ytz.shop.common.enums.AttributeType;
import com.ytz.shop.pojo.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
     * 根据分类ID 参数类型   查询参数信息
     * @param cateId  分类ID
     * @param deleted  未删除
     * @param type  参数类型
     * @return
     */
    List<Attribute> findByCateIdAndDeletedAndType(Long cateId, Integer deleted, AttributeType type);

    /**
     * 根据分类ID 参数类型  属性ID   查询参数信息
     * @param cateId
     * @param id
     * @param attributeType
     * @return
     */
    Attribute findByCateIdAndIdAndType(Long cateId, Long id, AttributeType attributeType);


    /**
     * 修改参数信息
     * @param attribute
     * @return
     */
    @Modifying
    @Query(value = "UPDATE Attribute a SET a.name = :#{#attr.name} WHERE a.id = :#{#attr.id} AND a.cateId = :#{#attr.cateId} AND a.type = :#{#attr.type}")
    int update(@Param("attr") Attribute attribute);

    /**
     * 删除属性
     * @param cateId
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "UPDATE Attribute SET DELETED = 1 WHERE ID = ?2 AND CATE_ID = ?1")
    int delete(Long cateId, Long id);
}
