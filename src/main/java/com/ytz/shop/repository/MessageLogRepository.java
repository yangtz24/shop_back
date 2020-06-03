package com.ytz.shop.repository;

import com.ytz.shop.pojo.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: MessageLogRepository
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/6/3 15:02
 */
@Repository
public interface MessageLogRepository extends JpaRepository<MessageLog, Long>, JpaSpecificationExecutor<MessageLog> {

    /**
     * 修改状态
     * @param msgId 消息唯一ID
     * @param status
     */
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE msg_log SET STATUS = ?2 WHERE MSG_ID = ?1")
    int updateStatus(String msgId, Integer status);

    /**
     * 修改重试次数
     * @param masId
     * @param date
     * @return
     */
    @Modifying
    @Query(nativeQuery = true,value = "UPDATE msg_log SET TRY_COUNT = TRY_COUNT + 1, NEXT_TRY_TIME = ?2 WHERE MSG_ID = ?1")
    int updateCount(String masId, Date date);


    /**
     * 根据status查询
     * @param status
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM msg_log WHERE STATUS = ?1 AND NEXT_TRY_TIME < SYSDATE()")
    List<MessageLog> findByStatus(Integer status);
}
