package com.ytz.shop.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName: AdminPermissionRelation
 * @Description: 用户权限信息
 * @author: yangtianzeng
 * @date: 2020/4/8 14:40
 */
@Entity
@Data
@Table(name = "admin_permission_relation")
public class AdminPermissionRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private Long adminId;

    private Long permissionId;

    private Integer type;

}
