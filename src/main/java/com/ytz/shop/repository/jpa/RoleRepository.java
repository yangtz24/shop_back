package com.ytz.shop.repository.jpa;

import com.ytz.shop.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: RoleRepository
 * @Description: 角色
 * @author: yangtianzeng
 * @date: 2020/4/14 10:11
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE ROLE SET status = ?2 WHERE id = ?1")
    int updateStatusById(Long id, Integer status);
}
