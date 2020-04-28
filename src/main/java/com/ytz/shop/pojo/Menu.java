package com.ytz.shop.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Menu
 * @Description: 菜单
 * @author: yangtianzeng
 * @date: 2020/4/11 9:27
 */
@Entity
@Table(name = "menu")
@Data
public class Menu implements Serializable {

    public static final Long ONE_MENU = 0L;
    private static final long serialVersionUID = 8880934244628827826L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String path;
    @Column(columnDefinition="INT default 0")
    private int queue;
    @Column(columnDefinition="INT default 0")
    private Long parentId;

    @Transient
    private List<Menu> children;
}
