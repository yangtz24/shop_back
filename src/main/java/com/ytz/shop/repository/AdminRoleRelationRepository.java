package com.ytz.shop.repository;

import com.ytz.shop.pojo.AdminRoleRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: AdminRoleRelationRepository
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/8 15:09
 */
@Repository
public interface AdminRoleRelationRepository extends JpaRepository<AdminRoleRelation, Long> {
}
