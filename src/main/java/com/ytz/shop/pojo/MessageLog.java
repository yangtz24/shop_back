package com.ytz.shop.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: MessageLog
 * @Description: 消息日志记录
 * @author: yangtianzeng
 * @date: 2020/6/3 14:54
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "msg_log")
public class MessageLog implements Serializable {

    public static final int SENDING = 0;
    public static final int SEND_SUCCESS = 1;
    public static final int SEND_FAILURE = 2;
    public static final int SPENDING = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("消息唯一标识")
    private String msgId;
    @ApiModelProperty("内容编号")
    private Long contentId;
    @ApiModelProperty("消息体")
    private String msg;
    @ApiModelProperty("交换机")
    private String exchange;
    @ApiModelProperty("路由键")
    private String routingKey;
    @ApiModelProperty("状态: 0投递中 1投递成功 2投递失败 3已消费")
    private Integer status;
    @ApiModelProperty("重试次数")
    private Integer tryCount;
    @ApiModelProperty("下一次重试时间")
    private Date nextTryTime;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;


}
