package com.heyou.springboot.controller.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author heyou
 */
@Aspect
@Component
public class AopConfig {

    private static final Logger logger = LoggerFactory.getLogger(AopConfig.class);

    private ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    /**
     * 横切点
     */
    @Pointcut("execution(* com.heyou.springboot.controller..*.*(..))")
    public void controllerLog() {

    }
    /**
     * 接收请求，并记录数据
     */
    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 记录请求内容，threadInfo存储所有内容
        Map<String, Object> threadInfo = new HashMap<>();
        logger.info("URL : " + request.getRequestURL());
        threadInfo.put("url", request.getRequestURL());
        logger.info("URI : " + request.getRequestURI());
        threadInfo.put("uri", request.getRequestURI());
        logger.info("HTTP_METHOD : " + request.getMethod());
        threadInfo.put("httpMethod", request.getMethod());
        logger.info("REMOTE_ADDR : " + request.getRemoteAddr());
        threadInfo.put("ip", request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        threadInfo.put("classMethod",
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        threadInfo.put("args", Arrays.toString(joinPoint.getArgs()));
        logger.info("USER_AGENT"+request.getHeader("User-Agent"));
        threadInfo.put("userAgent", request.getHeader("User-Agent"));
        threadLocal.set(threadInfo);
    }
    /**
     * 执行成功后处理
     */
    @AfterReturning(returning = "obj", pointcut = "controllerLog()")
    public void doAfterReturning(Object obj) {
        Map<String, Object> threadInfo = threadLocal.get();
        threadInfo.put("result", obj);
        // 处理完请求，返回内容
        logger.info("Response : " + obj);
    }
    /**
     * 获取执行时间
     */
    @Around(value = "controllerLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = proceedingJoinPoint.proceed();
        Map<String, Object> threadInfo = threadLocal.get();
        Long takeTime = System.currentTimeMillis() - startTime;
        threadInfo.put("takeTime", takeTime);
        logger.info("耗时：" + takeTime+"毫秒");
        threadLocal.set(threadInfo);
        return ob;
    }
    /**
     * 异常处理
     */
    @AfterThrowing(value = "controllerLog()", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();

        ServletRequestAttributes sra = (ServletRequestAttributes) ra;

        HttpServletRequest request = sra.getRequest();
        // 异常信息
        logger.error("{}接口调用异常，异常信息{}", request.getRequestURI(), throwable);
    }

}
