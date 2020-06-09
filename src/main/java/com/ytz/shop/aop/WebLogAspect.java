package com.ytz.shop.aop;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: WebLogAspect
 * @Description: TODO  日志切面类
 *      前置通知（Before）：在目标方法调用前调用通知功能；
 *      后置通知（After）：在目标方法调用之后调用通知功能，不关心方法的返回结果；
 *      返回通知（AfterReturning）：在目标方法成功执行之后调用通知功能；
 *      异常通知（AfterThrowing）：在目标方法抛出异常后调用通知功能；
 *      环绕通知（Around）：通知包裹了目标方法，在目标方法调用之前和之后执行自定义的行为。
 *
 * @author: basketBoy
 * @date: 2020/6/7
 * @Version: V1.0
 */
@Component
@Aspect
@Order(1)
@Slf4j
public class WebLogAspect {

    @Pointcut("execution(public * com.ytz.shop.controller.*.*(..))")
    public void webLog() {}

    /**
     * 前置通知  通知方法会在目标方法调用之前执行
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {

    }

    /**
     *后置通知  通知方法会在目标方法返回或抛出异常后执行
     */
    @After("webLog()")
    public void doAfter() {}

    /**
     * 返回通知  通知方法会在目标方法返回后执行
     * @param obj
     */
    @AfterReturning(value = "webLog()", returning = "obj")
    public void doAfterReturning(Object obj) {

    }

    /**
     * 环绕通知  通知方法会将目标方法封装起来
     * @param joinPoint
     * @return
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取开始时间
        long startTime = System.currentTimeMillis();
        // 获取当前请求的对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 记录请求信息
        WebLog webLog = new WebLog();
        // 获取执行结果
        Object result = joinPoint.proceed();
        // 获取方法签名
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        // 获取方法
        Method method = signature.getMethod();
        // 如果指定类型的注释（ApiOperation）存在于此元素上,返回true,否则false
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            // 获取方法的描述
            webLog.setDescription(apiOperation.value());
        }
        long endTime = System.currentTimeMillis();
        // 获取请求URI
        String requestUri = request.getRequestURI();
        // 获取请求得URL
        String requestUrl = request.getRequestURL().toString();
        webLog.setBasePath(StrUtil.removeSuffix(requestUrl, URLUtil.url(requestUrl).getPath()));
        webLog.setIp(request.getRemoteUser());
        webLog.setMethod(request.getMethod());
        webLog.setParameter(getParameter(method, joinPoint.getArgs()));
        webLog.setStartTime(startTime);
        webLog.setResult(result);
        webLog.setSpendTime((int) (endTime - startTime));
        webLog.setUri(requestUri);
        webLog.setUrl(requestUrl);
//        log.info("webLog--------> {}", JSONUtil.parse(webLog));
        return result;
    }

    private Object getParameter(Method method, Object[] args) {
        List<Object> arglist = new ArrayList<>();

        Parameter[] parameters = method.getParameters();
        if (ObjectUtil.isNotEmpty(parameters)) {
            for (int i = 0; i < parameters.length; i++) {
                //  将  RequestBody 注解修饰的参数作为请求参数
                RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
                if (requestBody != null) {
                    arglist.add(args[i]);
                }

                // 将 RequestParam 注解修饰的参数作为请求参数
                RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
                if (requestParam != null) {
                    HashMap<String, Object> map = new HashMap<>();
                    String key = parameters[i].getName();
                    if (!StringUtils.isEmpty(requestParam.value())) {
                        key = requestParam.value();
                    }
                    map.put(key, args[i]);
                    arglist.add(map);
                }
            }
        }
        log.info("argList.size()----------->{}", arglist.size());

        if (arglist.size() == 1) {
            return arglist.get(0);
        } else if(CollUtil.isEmpty(arglist)) {
            return null;
        } else {
            return arglist;
        }
    }


}
