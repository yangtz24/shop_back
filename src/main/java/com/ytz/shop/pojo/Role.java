package com.ytz.shop.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: Role
 * @Description: 角色信息
 * @author: yangtianzeng
 * @date: 2020/4/8 14:44
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "shop_role")
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
     * VO
     */
    @Transient
    private boolean state;
    @Transient
    private String createTimeStr;
}
