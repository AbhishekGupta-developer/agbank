package com.myorganisation.agbank.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BankAspect {

    //creating logger here
    Logger logger = LoggerFactory.getLogger(BankAspect.class);

    @Before("execution(* com.myorganisation.agbank.service.BankService.viewAccount(..))")
    public void printLogStatementsViewAccountBefore() {
        logger.info("viewAccount() method called!");
        logger.info("Start time: " + System.currentTimeMillis());
    }

    @After("execution(* com.myorganisation.agbank.service.BankService.viewAccount(..))")
    public void printLogStatementsViewAccountAfter() {
        logger.info("viewAccount() method completed!");
        logger.info("End time: " + System.currentTimeMillis());

    }

    @Pointcut(value = "execution(* com.myorganisation.agbank.service.BankService.checkBalance(..))")
    public void printLogStatementsCheckBalancePointcut() {

    }

    @Around(value = "printLogStatementsCheckBalancePointcut()")
    public void aroundAdvice(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("The method aroundAdvice() before invokation of  the method " + jp.getSignature().getName() + " method");

        try {
            jp.proceed();
        } finally {

        }

        System.out.println("The method aroundAdvice() after invokation of  the method " + jp.getSignature().getName() + " method");
    }

}
