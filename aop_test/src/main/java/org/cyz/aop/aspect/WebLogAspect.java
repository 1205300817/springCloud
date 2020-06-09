package org.cyz.aop.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author chengyz
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    /**
     * 定义切入点，切入点为org.cyz.aop.controller 下的所有函数
     */
    @Pointcut("execution(public * org.cyz.aop.controller.AopController.*(..))")
    public void webLog() {
    }

    /**
     * 前置通知：在连接点之前执行的通知
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("============= 我来源与Before ========================");
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("============= 我来源与AfterReturning ========================");
        log.info("RESPONSE : " + ret);
    }

    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("============= 处理异常：AfterThrowing =============");
        log.info("异常：{}", e.getMessage());
        log.info("异常类：{}", className);
        log.info("异常方法：{}", methodName);
        log.info("异常方法的参数：{}", args);
    }


}
