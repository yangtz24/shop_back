package com.ytz.shop.interceptor;

import com.ytz.shop.annotation.ReSubmitLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @ClassName: LockKeyGenerator
 * @Description: key 的生成策略
 * @author: yangtianzeng
 * @date: 2020/6/10 17:25
 */
public class LockKeyGenerator implements CacheKeyGenerator {
    @Override
    public String getLockKey(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final Method method = methodSignature.getMethod();
        final ReSubmitLock submitLock = method.getAnnotation(ReSubmitLock.class);
        String key = submitLock.key();
        final StringBuilder builder = new StringBuilder();
        return null;
    }
}
