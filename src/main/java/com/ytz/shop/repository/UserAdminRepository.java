package com.ytz.shop.repository;

import com.ytz.shop.pojo.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @ClassName: UserAdminRepository
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/8 15:04
 */
@Repository
public interface UserAdminRepository extends JpaRepository<UserAdmin, Long>, JpaSpecificationExecutor<UserAdmin> {
    /**
     *  根据用户名查询
     * @param userName
     * @return
     */
    UserAdmin findByUsername(String userName);

    /**
     * 根据昵称查询
     * @param nickName
     * @return
     */
    UserAdmin findByNickName(String nickName);

    /**
     * 更新用户最后一次登录时间
     * @param username
     * @param date
     */
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE SHOP_ADMIN SET login_time = ?2 WHERE id = ?1 ")
    void updateLoginDate(String username, Date date);

    /**
     * 修改用户状态
     * @param id
     * @param status
     * @return
     */
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE SHOP_ADMIN SET status = ?2 WHERE id = ?1")
    int updateStatus(Long id, Integer status);


    /**
     * 修改用户信息
     * @param userAdmin
     * @return
     */
    @Modifying
    @Query( nativeQuery = true, value = "UPDATE SHOP_ADMIN SET nick_name= :#{#admin.nickName}, email= :#{#admin.email}, mobile= :#{#admin.mobile}, update_time = :#{#admin.updateTime} WHERE id = :#{#admin.id}")
    int update(@Param("admin") UserAdmin userAdmin);
}
