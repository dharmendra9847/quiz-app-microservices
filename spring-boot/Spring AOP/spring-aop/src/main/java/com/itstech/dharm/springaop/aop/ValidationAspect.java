package com.itstech.dharm.springaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    private static final Logger logger = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.itstech.dharm.springaop.service.JobService.getJob(..)) && args(postId)")
    public Object validateAndUpdate(ProceedingJoinPoint pjp, int postId) throws Throwable {

        if (postId < 0) {

            logger.error("Invalid postID: {}", postId);

            logger.info("PostId is negative, updating it");

            postId = -postId;

            logger.info("PostId updated to positive");

            logger.info("Updated postID: {}", postId);
        }

        return pjp.proceed(new Object[]{postId});
    }
}
