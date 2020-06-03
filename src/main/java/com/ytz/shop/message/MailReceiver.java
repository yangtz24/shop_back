package com.ytz.shop.message;

import com.rabbitmq.client.Channel;
import com.ytz.shop.constants.MailConstants;
import com.ytz.shop.pojo.UserAdmin;
import com.ytz.shop.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName: MailReceiver
 * @Description: 消息接收者
 * @author: yangtianzeng
 * @date: 2020/6/3 10:26
 */
@Component
public class MailReceiver {

    public static final String MAIL_LOG = "mail_log";

    public static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private RedisUtils<Object> redisUtils;

    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Message<UserAdmin> message, Channel channel) throws IOException {
        UserAdmin userAdmin = message.getPayload();
        MessageHeaders messageHeaders = message.getHeaders();
        Long tag = (Long) messageHeaders.get(AmqpHeaders.DELIVERY_TAG);
        String msgId = (String) messageHeaders.get("spring_returned_message_correlation");
        //redis 中包含该 key，说明该消息已经被消费过
        if (redisUtils.containsKey(MAIL_LOG, msgId)) {
            logger.info(msgId + "------->消息已经被消费");
            // 确认消息已被消费
            channel.basicAck(tag, false);
            return;
        }
        // 收到消息，发送邮件
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(msg);
        try {
            messageHelper.setTo(userAdmin.getEmail());
            messageHelper.setFrom(mailProperties.getUsername());
            messageHelper.setSubject("欢迎注册");
            messageHelper.setSentDate(new Date());

            Context context = new Context();
            context.setVariable("name", userAdmin.getUsername());
            context.setVariable("nickName", userAdmin.getNickName());

            String mail = templateEngine.process("mail", context);
            messageHelper.setText(mail, true);
            mailSender.send(msg);

            // redis存入消息
            redisUtils.hset(MAIL_LOG, msgId, "basktBoy");
            channel.basicAck(tag, false);

            logger.info(msgId + "------->邮件发送成功");
        } catch (MessagingException e) {
            channel.basicNack(tag, false, true);
            e.printStackTrace();
            logger.error("邮件发送失败：" + e.getMessage());
        }

    }

}
