package com.ytz.shop.service;

import com.ytz.shop.pojo.Role;
import org.springframework.data.domain.Page;

/**
 * @ClassName: RoleService
 * @Description: 角色 业务接口
 * @author: yangtianzeng
 * @date: 2020/4/14 10:13
 */
public interface RoleService {
    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param key 关键字搜索
     * @return
     */
    Page<Role> list(Integer pageNum, Integer pageSize, String key);

    /**
     * 删除角色 根据ID
     * @param id
     */
    void remove(Long id);

    /**
     * 更改角色状态
     * @param id
     * @param status
     * @return
     */
    int modifyStatus(Long id, Integer status);
}
