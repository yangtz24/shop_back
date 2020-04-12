package com.ytz.shop.service;

import com.ytz.shop.pojo.Permission;
import com.ytz.shop.pojo.UserAdmin;
import org.springframework.data.domain.Page;

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

    /**
     * 查询用户列表 分页
     * @param pageNum   当前页号
     * @param pageSize   每页显示数量
     * @param key  关键字
     * @param phone  电话号
     * @param status   状态
     * @return
     */
    Page<UserAdmin> list(Integer pageNum, Integer pageSize, String key, String phone, Integer status);

    /**
     * 获取用户详情 根据ID
     * @param id
     * @return
     */
    UserAdmin detail(Long id);

    /**
     * 添加用户信息
     * @param admin
     * @return
     */
    UserAdmin add(UserAdmin admin);

    /**
     * 修改用户信息
     * @param userAdmin
     * @return
     */
    int edit(UserAdmin userAdmin);

    /**
     * 修改用户状态
     * @param id
     * @param status
     * @return
     */
    int modifyStatus(Long id, Integer status);

    /**
     * 删除用户
     * @param id
     * @return
     */
    void remove(Long id);
}
