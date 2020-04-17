package com.ytz.shop.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName: AdminRoleRelation
 * @Description: 用户角色信息
 * @author: yangtianzeng
 * @date: 2020/4/8 14:42
 */
@Entity
@Data
@Table(name = "admin_role_relation")
public class AdminRoleRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private Long adminId;

    private Long roleId;

}
