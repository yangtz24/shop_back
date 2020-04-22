package com.ytz.shop.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GoodsCategory
 * @Description: 商品分类信息
 * @author: yangtianzeng
 * @date: 2020/4/17 17:42
 */
@Entity
@Table(name = "goods_category")
@Data
@NoArgsConstructor
public class GoodsCategory implements Serializable {
    private static final long serialVersionUID = -1497986202894211267L;

    public static final int DELETED_NO = 0;
    public static final int DELETED_YES = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("父级ID")
    private Long parentId;

    @ApiModelProperty("分类级别")
    private Integer level;

    @ApiModelProperty("是否删除  0删除 1为删除")
    private Integer deleted;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("资源路径")
    private String src;


    @OneToMany(mappedBy = "parentCategory")
    private List<GoodsCategory> children = new ArrayList<>();

    @JsonBackReference
    @ManyToOne(targetEntity = GoodsCategory.class, cascade = CascadeType.ALL)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "parentId", insertable = false, updatable = false)
    private GoodsCategory parentCategory;
}
