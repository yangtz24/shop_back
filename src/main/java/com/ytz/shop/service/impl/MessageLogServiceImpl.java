package com.ytz.shop.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ytz.shop.pojo.MessageLog;
import com.ytz.shop.repository.MessageLogRepository;
import com.ytz.shop.service.MessageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: MessageLogServiceImpl
 * @Description: 消息记录日志 业务实现类
 * @author: yangtianzeng
 * @date: 2020/6/3 15:13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageLogServiceImpl implements MessageLogService {

    @Autowired
    private MessageLogRepository messageLogRepository;

    @Override
    public int add(MessageLog messageLog) {
        MessageLog log = messageLogRepository.save(messageLog);
        int result = 0;
        if (ObjectUtil.isNotEmpty(log)) {
            result = 1;
        }
        return result;
    }

    @Override
    public int update(MessageLog messageLog) {
        MessageLog newMessageLog = messageLogRepository.saveAndFlush(messageLog);
        int result =  0;
        if (ObjectUtil.isNotEmpty(newMessageLog)) {
            result = 1;
        }
        return result;
    }

    @Override
    public int updateStatus(String msgId, Integer status) {
        int result = messageLogRepository.updateStatus(msgId, status);
        return result;
    }

    @Override
    public int updateCount(String msgId, Date tryDate) {
        int result = messageLogRepository.updateCount(msgId, tryDate);
        return result;
    }

    @Override
    public List<MessageLog> getMessageLogByStatus(Integer status) {
        List<MessageLog> logList = messageLogRepository.findByStatus(status);
        return logList;
    }
}
