package com.ytz.shop.vo;

import com.ytz.shop.pojo.Permission;

import java.util.List;

/**
 * @ClassName: PermissionVO
 * @Description: 权限VO
 * @author: yangtianzeng
 * @date: 2020/4/16 11:40
 */
public interface PermissionVO {

    Long getId();

    String getName();

    Long getPid();

    String getValue();

    String getUri();

    Integer getType();

    Integer getStatus();

    List<Permission> getChildren();

    Permission getParentPermission();
}
