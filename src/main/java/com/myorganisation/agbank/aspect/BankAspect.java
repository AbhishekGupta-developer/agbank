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
    public void loggingPointcutUniversal() {}

    @Pointcut("execution(* com.myorganisation.agbank.controller.BankController.checkHealth(..))")
    public void loggingPointcutCheckHealth() {}

    @Pointcut("execution(* com.myorganisation.agbank.controller.BankController.createDummyAccount(..))")
    public void loggingPointcutCreateDummyAccount() {}

    @Before("loggingPointcutCheckHealth()")
    public void beforeAdviceLoggingPointcutUniversal(JoinPoint joinPoint) {
        logger.info("@Before advice: {} method invoked! At {}. Signature: {}",
                joinPoint.getSignature().getName(),
                FORMATTER.format(LocalDateTime.now()),
                joinPoint.getSignature()
        );
    }

    @After("loggingPointcutCheckHealth()")
    public void afterAdviceLoggingPointcutUniversal(JoinPoint joinPoint) {
        logger.info("@After advice: {} method invoked! At {}. Signature: {}",
                joinPoint.getSignature().getName(),
                FORMATTER.format(LocalDateTime.now()),
                joinPoint.getSignature()
        );
    }

    @Around("loggingPointcutUniversal()")
    public Object aroundAdviceLoggingPointcutUniversal(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        logger.info("@Around advice: before invocation of {} method! At {}. Signature: {}",
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

        logger.info("@Around advice: after invocation of {} method! At {}. Signature: {}",
                proceedingJoinPoint.getSignature().getName(),
                FORMATTER.format(LocalDateTime.now()),
                proceedingJoinPoint.getSignature()
        );

        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Execution time of {} method: {} ms", proceedingJoinPoint.getSignature().getName(), timeTaken);

        //Return the result to the caller
        return result;
    }

    @AfterThrowing(pointcut = "loggingPointcutCreateDummyAccount()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.error("@AfterThrowing advice: An exception occurred in {} method. Message: {}", joinPoint.getSignature().getName(), e.getMessage(), e);
    }

    @AfterReturning(pointcut = "loggingPointcutCreateDummyAccount()")
    public void afterReturning(JoinPoint joinPoint) {
        logger.info("@AfterReturning advice: {} method completed", joinPoint.getSignature().getName());
    }

}
