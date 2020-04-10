package com.ytz.shop.repository;

import com.ytz.shop.pojo.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @ClassName: UserAdminRepository
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/8 15:04
 */
@Repository
public interface UserAdminRepository extends JpaRepository<UserAdmin, Long> {
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
     * 更新用户最后一次邓论时间
     * @param username
     * @param date
     */
    @Modifying
    @Query(nativeQuery = true, value = "update shop_admin set login_time = ?2 where id = ?1 ")
    void updateLoginDate(String username, Date date);
}
