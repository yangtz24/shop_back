package com.ytz.shop.dto;

import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: RoleVO
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/20 18:12
 */
@NoArgsConstructor
public class RoleDTO implements Serializable {

    private Long[] roleIds;

    public Long[] getRoleIds() {
        return roleIds;
    }
}
