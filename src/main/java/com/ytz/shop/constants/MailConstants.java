package com.ytz.shop.constants;

/**
 * @ClassName: MailConstants
 * @Description: 邮件消息 常量设置
 * @author: yangtianzeng
 * @date: 2020/6/3 10:41
 */
public class MailConstants {

    /**
     * 消息投递中
     */
    public static final Integer DELIVERING = 0;
    /**
     * 消息投递成功
     */
    public static final Integer SUCCESS = 1;
    /**
     * 消息投递失败
     */
    public static final Integer FAILURE = 2;
    /**
     * 最大重试次数
     */
    public static final Integer MAX_TRY_COUNT = 3;
    /**
     * 消息超时时间
     */
    public static final Integer MSG_TIMEOUT = 1;

    /**
     * 队列名称
     */

    public static final String MAIL_QUEUE_NAME = "basketboy.mail.queue";

    /**
     * 交换机名称
     */
    public static final String MAIL_EXCHANGE_NAME = "basketboy.mail.exchange";

    /**
     * 路由键名称
     */
    public static final String MAIL_ROUTING_KEY_NAME = "basketboy.mail.routing.key";
}
