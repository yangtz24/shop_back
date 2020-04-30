package com.ytz.shop.pojo;

import com.ytz.shop.common.enums.AttributeType;
import com.ytz.shop.common.enums.AttributeWriteType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName: CategoryParam
 * @Description: 分类参数
 * @author: yangtianzeng
 * @date: 2020/4/22 17:10
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "attribute")
@DynamicInsert
@DynamicUpdate
public class Attribute implements Serializable {

    private static final long serialVersionUID = -847751782790472600L;

    /**
     * 未删除
     */
    public static final int UN_DELETED = 0;
    /**
     * 已删除
     */
    public static final int DELETED = 1;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("分类ID")
    private Long cateId;

    @ApiModelProperty("属性名称")
    private String name;

    @ApiModelProperty("only:输入框(唯一)  many:后台下拉列表/前台单选框")
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AttributeType type;

    @ApiModelProperty("manual:手工录入  list:从列表选择")
    @Column(name = "write_type")
    @ColumnDefault("manual")
    @Enumerated(EnumType.STRING)
    private AttributeWriteType writeType;

    @ApiModelProperty("可选值列表信息,例如颜色：白色,红色,绿色,多个可选值通过逗号分隔")
    @Column(name = "vals")
    private String values;

    @ApiModelProperty("删除标志 0:未删除  1:已删除")
    private Integer deleted;

    // ERROR 8008 --- [  restartedMain] com.alibaba.druid.pool.DruidDataSource   : discard connection
 /*   @ManyToOne
    @JoinColumn(name = "cateId", insertable = false, updatable = false)
    private GoodsCategory category;*/

}
