package com.ytz.shop.repository.jpa;

import com.ytz.shop.pojo.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PermissionRepository
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/9 10:30
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {

    /**
     * 根据用户ID查询权限信息
     * @param adminId
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT\n" +
            "            p.*\n" +
            "        FROM\n" +
            "            admin_role_relation ar\n" +
            "            LEFT JOIN role r ON ar.role_id = r.id\n" +
            "            LEFT JOIN role_permission_relation rp ON r.id = rp.role_id\n" +
            "            LEFT JOIN permission p ON rp.permission_id = p.id\n" +
            "        WHERE\n" +
            "            ar.admin_id = ?1\n" +
            "            AND p.id IS NOT NULL\n" +
            "            AND p.id NOT IN (\n" +
            "                SELECT\n" +
            "                    p.id\n" +
            "                FROM\n" +
            "                    admin_permission_relation pr\n" +
            "                    LEFT JOIN permission p ON pr.permission_id = p.id\n" +
            "                WHERE\n" +
            "                    pr.type = - 1\n" +
            "                    AND pr.admin_id = ?1\n" +
            "            )\n" +
            "        UNION\n" +
            "        SELECT\n" +
            "            p.*\n" +
            "        FROM\n" +
            "            admin_permission_relation pr\n" +
            "            LEFT JOIN permission p ON pr.permission_id = p.id\n" +
            "        WHERE\n" +
            "            pr.type = 1\n" +
            "            AND pr.admin_id = ?1")
    List<Permission> findByAdminIdPermissionList(Long adminId);

    /**
     * 根据父ID查询
     * @param pid
     * @return
     */
    List<Permission> findByParentId(Long pid);
}
