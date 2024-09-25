package com.myorganisation.agbank.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BankAspect {

    //creating logger here
    Logger logger = LoggerFactory.getLogger(BankAspect.class);

    @Before("execution(* com.myorganisation.agbank.service.BankService.viewAccount(..))")
    public void printLogStatementsBefore() {
        logger.info("viewAccount() method called!");
    }

}
