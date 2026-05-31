package com.itstech.dharm.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitorAspect {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceMonitorAspect.class);

//    @Around("execution(* com.itstech.dharm.springaop.service.JobService.getJob(..))")
//    public Object monitorTime(ProceedingJoinPoint pjp) {
//
//        long start = System.currentTimeMillis();
//
//        Object obj = null;
//
//        try {
//            obj = pjp.proceed();
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//
//        long end = System.currentTimeMillis();
//
//        logger.info("Time taken: {}", (end - start) + " ms");
//
//        return obj;
//    }

    @Around("execution(* com.itstech.dharm.springaop.service.JobService.*(..))")
    public Object monitorTime(ProceedingJoinPoint pjp) {

        long start = System.currentTimeMillis();

        Object obj = null;

        try {
            obj = pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();

        logger.info("Time taken: " + pjp.getSignature().getName() + " : " + (end - start) + " ms");

        return obj;
    }
}
