package com.ytz.shop.repository.jpa;

import com.ytz.shop.pojo.AdminPermissionRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: AdminPermissionRelationRepository
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/9 9:58
 */
@Repository
public interface AdminPermissionRelationRepository extends JpaRepository<AdminPermissionRelation, Long> {

}
