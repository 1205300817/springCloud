package org.cyz.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author chengyz
 */
@Slf4j
@Aspect
@Component
public class AudienceAspect {

    @Pointcut("execution(* org.cyz.aop.concert.Performance.perform(..))")
    public void performance() {
    }

    @Before("performance()")
    public void silenceCellPhones() {
        log.info("============= Before =============");
        log.info("silencing cell phones");
    }

    @Before("performance()")
    public void takeSeats() {
        log.info("============= Before =============");
        log.info("taking seats");
    }

    @AfterReturning("performance()")
    public void applause() {
        log.info("============= AfterReturning =============");
        log.info("CLAP CLAP CLAP!!!");
    }

    @AfterThrowing(pointcut = "performance()", throwing = "e")
    public void demandRefund(JoinPoint joinPoint, Exception e) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("============= AfterThrowing =============");
        log.info("demanding a refund");
        log.info("============= 处理异常：AfterThrowing =============");
        log.info("异常：{}", e.getMessage());
        log.info("异常类：{}", className);
        log.info("异常方法：{}", methodName);
        log.info("异常方法的参数：{}", args);
    }


}
