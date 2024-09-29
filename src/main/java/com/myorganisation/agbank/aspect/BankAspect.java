package com.myorganisation.agbank.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class BankAspect {

    //creating logger here
    Logger logger = LoggerFactory.getLogger(BankAspect.class);

    @Pointcut("execution(* com.myorganisation.agbank.controller.BankController.*(..))")
    public void loggingPointcut() {}

    @Before("loggingPointcut()")
    public void beforeAdviceLoggingPointcut(JoinPoint joinPoint) {
        logger.info("@Before advice: {} method invoked! at {}",
                joinPoint.getSignature().getName(),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now())
        );
        logger.info("JoinPoint: {}", joinPoint.getSignature());
    }

    @After("loggingPointcut()")
    public void afterAdviceLoggingPointcut(JoinPoint joinPoint) {
        logger.info("@After advice: {} method invoked! at {}",
                joinPoint.getSignature().getName(),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now())
        );
        logger.info("JoinPoint: {}", joinPoint.getSignature());
    }

    @Around("loggingPointcut()")
    public Object aroundAdviceLoggingPointcut(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("@Around advice: before invocation of {} method! at {}",
                proceedingJoinPoint.getSignature().getName(),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now())
        );
        logger.info("JoinPoint: {}", proceedingJoinPoint.getSignature());

        //Proceed with the actual method invocation
        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            logger.info("Exception occurred: {}", e.getMessage());
            throw e;
        } finally {}

        logger.info("@Around advice: after invocation of {} method! at {}",
                proceedingJoinPoint.getSignature().getName(),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now())
        );
        logger.info("JoinPoint: {}", proceedingJoinPoint.getSignature());

        //Return the result to the caller
        return result;
    }

}
