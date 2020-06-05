package com.ytz.shop.config;

import com.ytz.shop.constants.MailConstants;
import com.ytz.shop.pojo.MessageLog;
import com.ytz.shop.service.MessageLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RabbitMqConfig
 * @Description: rabbitMq配置信息类
 * @author: yangtianzeng
 * @date: 2020/6/3
 */
@Configuration
public class RabbitMqConfig {

    public final static Logger logger = LoggerFactory.getLogger(RabbitMqConfig.class);

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Autowired
    private MessageLogService messageLogService;

    @Bean
    public Queue mailQueue() {
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }

    /**
     * 队列绑定的交换机
     * @return
     */
    @Bean
    public DirectExchange mailDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(MailConstants.MAIL_EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    /**
     * 绑定消息队列 绑定 到交换机
     * @param exchange 交换机
     * @param queue 队列
     * @return
     */
    @Bean
    public Binding mailBinding(DirectExchange exchange, Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }

    @Bean
    RabbitTemplate rabbitTemplate() {
        cachingConnectionFactory.setPublisherConfirms(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            String msgId = data.getId();
            if (ack) {
                logger.info(msgId + "------->消息发送成功");
                //修改数据库中的记录，消息投递成功
                messageLogService.updateStatus(msgId, MessageLog.SEND_SUCCESS);
            } else {
                logger.info(msgId + "------->消息发送失败");
                messageLogService.updateStatus(msgId, MessageLog.SEND_FAILURE);
            }
        });
        /*rabbitTemplate.setReturnCallback((msg, repCode, repText, exchange, routingKey) -> {
            logger.info("消息发送失败");
        });*/
        return rabbitTemplate;
    }

}
