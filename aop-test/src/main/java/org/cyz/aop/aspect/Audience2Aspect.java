package org.cyz.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author chengyz
 */
@Slf4j
@Aspect
@Component
public class Audience2Aspect {

    @Pointcut("execution(* org.cyz.aop.concert.Performance.perform2(..))")
    public void performance2() {
    }

    @Around("performance2()")
    public void watchPerformance(ProceedingJoinPoint jp){
        try {
            System.out.println("silencing cell phones");
            System.out.println("taking seats");
            jp.proceed();
            System.out.println("CLAP CLAP CLAP!!!");
        } catch (Throwable throwable) {
            log.error(throwable.getMessage());
            System.out.println("demanding a refund");
        }
    }

}
