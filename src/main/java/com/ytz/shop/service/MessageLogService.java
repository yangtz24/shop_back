package com.ytz.shop.service;

import com.ytz.shop.pojo.MessageLog;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: MessageLogService
 * @Description: 消息日志记录
 * @author: yangtianzeng
 * @date: 2020/6/3 15:03
 */
public interface MessageLogService {

    /**
     * 添加
     * @param messageLog
     * @return
     */
    int add(MessageLog messageLog);

    /**
     * 修改
     * @param messageLog
     * @return
     */
    int update(MessageLog messageLog);

    /**
     * 修改状态
     * @param msgId
     * @param status 0投递中 1投递成功 2投递失败 3已消费
     * @return
     */
    int updateStatus(String msgId, Integer status);

    /**
     * 修改投递次数
     * @param msgId
     * @param tryDate
     * @return
     */
    int updateCount(String msgId, Date tryDate);

    /**
     * 根据状态   获取消息日志
     * @param status
     * @return
     */
    List<MessageLog> getMessageLogByStatus(Integer status);

}

