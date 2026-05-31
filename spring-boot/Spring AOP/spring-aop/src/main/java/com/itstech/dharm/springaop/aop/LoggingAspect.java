package com.itstech.dharm.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // return type, class_name.methode_name(args)

//    @Before("execution(* *.*(..))")
//    @Before("execution(* com.itstech.dharm.springaop.service.JobService.*(..))")
//    public void logMethodCall() {
//        LOGGER.info("Method Called");
//    }

//    @Before("execution(* com.itstech.dharm.springaop.service.JobService.getJob(..))")
//    public void logMethodCall(JoinPoint jp) {
//        LOGGER.info("Method Called {}", jp.getSignature().getName());
//    }

    @Before("execution(* com.itstech.dharm.springaop.service.JobService.getJob(..)) || execution(* com.itstech.dharm.springaop.service.JobService.updateJob(..))")
    public void logMethodCall(JoinPoint jp) {
        LOGGER.info("Method Called {}", jp.getSignature().getName());
    }

    @After("execution(* com.itstech.dharm.springaop.service.JobService.getJob(..)) || execution(* com.itstech.dharm.springaop.service.JobService.updateJob(..))")
    public void logMethodExecuted(JoinPoint jp) {
        LOGGER.info("Finally method executed {}", jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.itstech.dharm.springaop.service.JobService.getJob(..)) || execution(* com.itstech.dharm.springaop.service.JobService.updateJob(..))")
    public void logMethodCrashed(JoinPoint jp) {
        LOGGER.info("Method has some issues {}", jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.itstech.dharm.springaop.service.JobService.getJob(..)) || execution(* com.itstech.dharm.springaop.service.JobService.updateJob(..))")
    public void logMethodExecutedSuccess(JoinPoint jp) {
        LOGGER.info("Method Executed Successfully {}", jp.getSignature().getName());
    }
}
