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

}
