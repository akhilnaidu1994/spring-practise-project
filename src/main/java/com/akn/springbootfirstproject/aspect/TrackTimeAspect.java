package com.akn.springbootfirstproject.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class TrackTimeAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(com.akn.springbootfirstproject.aspect.TrackTime)")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("time take by method {} is {} milliseconds",joinPoint.getSignature().getName(),endTime-startTime);
        return proceed;
    }
}
