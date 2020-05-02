package com.ytz.shop.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName: Order
 * @Description: 订单信息
 * @author: yangtianzeng
 * @date: 2020/5/2 10:19
 */
@Entity
@Table(name = "t_order")
@Data
@NoArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = -4592814643064866139L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("用户编号")
    private Long userId;

    @ApiModelProperty("订单编号")
    private String orderNumber;

    @ApiModelProperty("订单价格")
    private BigDecimal orderPrice;

    @ApiModelProperty("支付方式  0未支付 1支付宝  2微信  3其他")
    private Integer payMethod;

    @ApiModelProperty("订单是否已经发货 0未发货 1已发货")
    private Integer isSend;

    @ApiModelProperty("支付宝交易流水号")
    private String tradeNo;

    @ApiModelProperty("发票抬头 0个人 1公司")
    private String orderInvoiceTitle;

    @ApiModelProperty("发票公司名称")
    private String orderInvoiceCompany;

    @ApiModelProperty("发票内容")
    private String orderInvoiceContent;

    @ApiModelProperty("consignee 收货人地址")
    private String consigneeAddress;

    @ApiModelProperty("订单状态： 0未付款、1已付款")
    private Integer payStatus;

    @ApiModelProperty("订单生成时间")
    private LocalDateTime createTime;

    @ApiModelProperty("订单更新时间")
    private LocalDateTime updateTime;
}
