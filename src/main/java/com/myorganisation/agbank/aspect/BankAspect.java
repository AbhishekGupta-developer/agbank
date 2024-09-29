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

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @Pointcut("execution(* com.myorganisation.agbank.controller.BankController.*(..))")
    public void loggingPointcut() {}

    @Before("loggingPointcut()")
    public void beforeAdviceLoggingPointcut(JoinPoint joinPoint) {
        logger.info("@Before advice: {} method invoked! at {}",
                joinPoint.getSignature().getName(),
                FORMATTER.format(LocalDateTime.now())
        );
        logger.info("JoinPoint: {}", joinPoint.getSignature());
    }

    @After("loggingPointcut()")
    public void afterAdviceLoggingPointcut(JoinPoint joinPoint) {
        logger.info("@After advice: {} method invoked! at {}",
                joinPoint.getSignature().getName(),
                FORMATTER.format(LocalDateTime.now())
        );
        logger.info("JoinPoint: {}", joinPoint.getSignature());
    }

    @Around("loggingPointcut()")
    public Object aroundAdviceLoggingPointcut(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        logger.info("@Around advice: before invocation of {} method! At {}. More info: JoinPoint: {}",
                proceedingJoinPoint.getSignature().getName(),
                FORMATTER.format(LocalDateTime.now()),
                proceedingJoinPoint.getSignature()
        );

        //Proceed with the actual method invocation
        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            logger.error("Exception occurred in method {}: {} - Stack trace: ", proceedingJoinPoint.getSignature().getName(), e.getMessage(), e);
            throw e;
        }

        logger.info("@Around advice: after invocation of {} method! At {}. More info: JoinPoint: {}",
                proceedingJoinPoint.getSignature().getName(),
                FORMATTER.format(LocalDateTime.now()),
                proceedingJoinPoint.getSignature()
        );

        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Execution time of {} method: {} ms", proceedingJoinPoint.getSignature().getName(), timeTaken);

        //Return the result to the caller
        return result;
    }

}
