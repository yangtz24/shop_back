package com.ytz.shop.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: SubmitLock
 * @Description: 重复提交，接口幂等性解决
 * @author: yangtianzeng
 * @date: 2020/6/10 16:45
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ReSubmitLock {

    /**
     * 名称
     * @return
     */
    String key() default "";

    /**
     * 过期时间
     * @return
     */
    int expire() default 5;

}
