package com.ytz.shop.aop;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.ytz.shop.annotation.ReSubmitLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: LockMethodAspect
 * @Description: 接口幂等性
 * @author: yangtianzeng
 * @date: 2020/6/10 16:54
 */
@Aspect
@Configuration
public class LockMethodAspect {

    private static final Cache<String, Object> CACHES = CacheBuilder.newBuilder()
            // 最大缓存 100 个
            .maximumSize(1000)
            // 设置写缓存后 5 秒钟过期
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();


    @Pointcut("execution(public * com.ytz.shop.controller.*.*(..))) && @@annotation(com.ytz.shop.annotation.SubmitLock)")
    public void lockMethodAspect() {}

    @Around("lockMethodAspect()")
    public Object annotationInterceptor(ProceedingJoinPoint joinPoint) {
        Signature pointSignature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) pointSignature;
        Method method = methodSignature.getMethod();
        ReSubmitLock submitLock = method.getAnnotation(ReSubmitLock.class);
        String key = getKey(submitLock.key(), joinPoint.getArgs());
        if (!StringUtils.isEmpty(key)) {
            if (CACHES.getIfPresent(key) != null) {
                throw new RuntimeException("请勿重复操作");
            }

            // 第一次，则将 key 放入缓存
            CACHES.put(key, key);
        }

        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("系统异常");
        } finally {
            // 销毁key,释放锁
            CACHES.invalidate(key);
        }


    }

    /**
     * key的生成方式
     * @param keyExpress
     * @param args
     * @return
     */
    private String getKey(String keyExpress, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            keyExpress = keyExpress.replace("arg[" + i + "]", args[i].toString());
        }
        return keyExpress;
    }
}
