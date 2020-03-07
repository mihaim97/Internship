package com.mihai.project.library.aop;

import com.mihai.project.library.entity.rent.BookRent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BookRentAOP {

    private static final Logger logger = LoggerFactory.getLogger("RENT");

    @AfterReturning(value = "@annotation(com.mihai.project.library.annotation.BookRentAOP)", returning = "bookRent")
    public void afterEmployeeRentABook(JoinPoint joinPoint, Object bookRent){
        if(bookRent != null){
            if(bookRent instanceof BookRent){
                BookRent rent = (BookRent) bookRent;
                logger.info("Book: [" + rent.getBook().getTitle() + ", " + rent.getBook().getId() +  "]" + ", Copy: [" + rent.getCopy().getCode() + "]"
                            +" was rented. Employee: " + rent.getUser().getUsername());
            }
        }
    }

}
