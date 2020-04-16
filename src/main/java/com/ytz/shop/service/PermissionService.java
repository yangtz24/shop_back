package com.ytz.shop.service;

import com.ytz.shop.pojo.Permission;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName: PermissionService
 * @Description: 权限 业务接口
 * @author: yangtianzeng
 * @date: 2020/4/15 14:31
 */
public interface PermissionService {

    /**
     * 获取一级、二级、三级
     * @return
     */
    List<Permission> getParentAndChildren();

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param key
     * @return
     */
    Page<Permission> list(Integer pageNum, Integer pageSize, String key);
}
