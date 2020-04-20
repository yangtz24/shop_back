package com.ytz.shop.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Role
 * @Description: 角色信息
 * @author: yangtianzeng
 * @date: 2020/4/8 14:44
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "后台用户数量")
    private Integer adminCount;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 1. 多对多，关系维护端，负责多对多关系的绑定与解除
     * 2. @JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端
     * 3. inverseJoinColumns指定外键的名字，要关联的关系被维护端
     * 4. 默认生成的关联表名称为主表表名+下划线+从表表名
     */
    @JsonIgnoreProperties("roleList")
    @ManyToMany
    @JoinTable(name = "role_permission_relation",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissionList = new ArrayList<>();

    @JsonIgnoreProperties("roles")
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<UserAdmin> adminList;

    /**
     * VO
     */
    @Transient
    private boolean state;
    @Transient
    private String createTimeStr;
}
