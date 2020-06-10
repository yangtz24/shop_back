package com.ytz.shop.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @ClassName: CacheKeyGenerator
 * @Description: key生成器
 * @author: yangtianzeng
 * @date: 2020/6/10 17:23
 */
public interface CacheKeyGenerator {
    /**
     * 根据参数，生成指定的KEY
     * @param joinPoint
     * @return
     */
    String getLockKey(ProceedingJoinPoint joinPoint);
}
