package com.ytz.shop.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UserAdmin
 * @Description: 用户信息
 * @author: yangtianzeng
 * @date: 2020/4/8 14:35
 */
@Entity
@Data
@Table(name = "shop_admin")
public class UserAdmin implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 启用
     */
    public static final int STATUS_ENABLE = 1;
    /**
     * 禁用
     */
    public static final int STATUS_DISABLE = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "备注信息")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Integer status;
}
