package com.ytz.shop.service;

import com.ytz.shop.pojo.Permission;
import com.ytz.shop.pojo.UserAdmin;

import java.util.List;

/**
 * @ClassName: AdminService
 * @Description: 后台用户业务接口
 * @author: yangtianzeng
 * @date: 2020/4/8 14:27
 */
public interface AdminService {
    /**
     * 注册
     * @param userAdmin
     * @return
     */
    UserAdmin register(UserAdmin userAdmin);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    UserAdmin getAdminByUsername(String username);

    /**
     * 获取权限列表，根据用户ID
     * @param adminId
     * @return
     */
    List<Permission> getPermissionList(Long adminId);
}
