package com.ytz.shop.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName: Goods
 * @Description: 商品信息
 * @author: yangtianzeng
 * @date: 2020/4/28 11:41
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = -2564651299424111890L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品价格")
    private BigDecimal price;

    @ApiModelProperty("商品数量")
    private Integer amount;

    @ApiModelProperty("商品重量")
    private BigDecimal weight;

    @ApiModelProperty("商品分类ID")
    private Long cateId;

    @ApiModelProperty("商品简介")
    private String introduce;

    @ApiModelProperty("商品大logo")
    private String bigLogo;

    @ApiModelProperty("商品小logo")
    private String smallLogo;

    @ApiModelProperty("0:正常  1:删除")
    private Integer deleted;

    @ApiModelProperty("添加时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("商品状态 0: 未通过 1: 审核中 2: 已审核")
    private Integer status;

    @ApiModelProperty("是否促销 0: 是 1:否")
    private Integer isPromote;

    @ApiModelProperty("热卖数量")
    private Integer hotNumber;

    @ApiModelProperty("一级分类ID")
    private Long cateOneId;

    @ApiModelProperty("一级分类ID")
    private Long cateSecondId;

    @ApiModelProperty("一级分类ID")
    private Long cateThirdId;


}
