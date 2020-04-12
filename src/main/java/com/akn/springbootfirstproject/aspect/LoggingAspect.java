package com.akn.springbootfirstproject.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.akn.springbootfirstproject.controller.UserController.*(..))")
    public void before(JoinPoint joinPoint){
        this.logger.info("logging before method {}",joinPoint.getSignature().getName());
    }
}
