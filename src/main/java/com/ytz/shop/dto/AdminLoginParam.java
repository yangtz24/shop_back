package com.ytz.shop.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @ClassName: AdminLoginParam
 * @Description: 用户登录参数
 * @author: yangtianzeng
 * @date: 2020/4/8 14:56
 */
@Getter
@Setter
public class AdminLoginParam implements Serializable {

    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;
}
