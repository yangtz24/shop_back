package com.ytz.shop.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName: RoleVO
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/20 18:12
 */
@Setter
@Getter
@NoArgsConstructor
public class RoleVO implements Serializable {

    private Long[] roleIds;
}
