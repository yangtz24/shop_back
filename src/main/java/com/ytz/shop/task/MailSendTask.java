package com.ytz.shop.task;

import cn.hutool.core.collection.CollectionUtil;
import com.ytz.shop.constants.MailConstants;
import com.ytz.shop.pojo.MessageLog;
import com.ytz.shop.pojo.UserAdmin;
import com.ytz.shop.service.AdminService;
import com.ytz.shop.service.MessageLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: MailSendTask
 * @Description: 定时任务 处理发送失败的消息
 * @author: yangtianzeng
 * @date: 2020/6/3 16:08
 */
@Component
public class MailSendTask {

    @Autowired
    private MessageLogService messageLogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AdminService adminService;

    /**
     * 10 秒重试一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void mailSendTask() {
        List<MessageLog> logList = messageLogService.getMessageLogByStatus(MessageLog.SENDING);
        if (CollectionUtil.isEmpty(logList)) {
            return;
        }

        logList.forEach(log -> {
            // 超过重试次数 直接将这条消息状态设置为 发送失败
            if (log.getTryCount() >= MailConstants.MAX_TRY_COUNT) {
                messageLogService.updateStatus(log.getMsgId(), MailConstants.FAILURE);
            } else {
                // 修改重试次数
                messageLogService.updateCount(log.getMsgId(), new Date());
                Long contentId = log.getContentId();
                UserAdmin admin = adminService.detail(contentId);
                rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, admin, new CorrelationData(log.getMsgId()));
            }
        });

    }
}
