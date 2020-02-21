package com.mihai.project.library.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class QueryBookAspect {

    private static final Logger logger = LoggerFactory.getLogger(QueryBookAspect.class);

    @After("execution(* com.mihai.project.library.dao.*.BookDAOImpl.queryBook(..))")
    public void emptyResultDataAccessException(){
        logger.info("Query a single book");
    }

    @AfterReturning(value = "@annotation(com.mihai.project.library.annotation.BookAOP)", returning = "returnedValue")
    public void afterBookAdded(JoinPoint joinPoint, Object returnedValue){
        if(returnedValue != null){
            if(returnedValue instanceof Number){
                Number number = (Number) returnedValue;
                logger.info("Book with id {} was added ", number.intValue());
            }
        }
    }

}
