package com.ytz.shop.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName: UserAdmin
 * @Description: 用户信息
 * @author: yangtianzeng
 * @date: 2020/4/8 14:35
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
public class UserAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "备注信息")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime loginTime;


    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Integer status;

    @JsonIgnoreProperties("adminList")
    @ManyToMany
    @JoinTable(name = "admin_role_relation",
            joinColumns = @JoinColumn(name = "adminId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roles;

    // VO
    @Transient
    private boolean state;
    @Transient
    private String createTimeStr;
    @Transient
    private String updateTimeStr;
}
