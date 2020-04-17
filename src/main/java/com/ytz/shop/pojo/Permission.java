package com.ytz.shop.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: Permission
 * @Description: 权限信息
 * @author: yangtianzeng
 * @date: 2020/4/8 14:43
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @ApiModelProperty(value = "父级权限id")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "权限值")
    private String value;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
    private Integer type;

    @ApiModelProperty(value = "前端资源路径")
    private String uri;

    @ApiModelProperty(value = "启用状态；0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @OneToMany(mappedBy = "parentPermission")
    private List<Permission> children = new ArrayList<>();

    @JsonBackReference
    @ManyToOne(targetEntity = Permission.class, cascade = CascadeType.ALL)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "parentId", insertable = false, updatable = false)
    private Permission parentPermission;

    @JsonIgnoreProperties("permissionList")
    @ManyToMany(mappedBy = "permissionList", cascade = CascadeType.ALL)
    private List<Role> roleList;
}
