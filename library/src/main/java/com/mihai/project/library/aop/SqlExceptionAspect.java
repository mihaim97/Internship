package com.mihai.project.library.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SqlExceptionAspect {

    private static final Logger logger = LoggerFactory.getLogger(SqlExceptionAspect.class);

    @After("execution(* com.mihai.project.library.dao.*.BookDAOImpl.queryBook(..))")
    public void emptyResultDataAccessException(){
        logger.info("Query a single book");
    }

}
