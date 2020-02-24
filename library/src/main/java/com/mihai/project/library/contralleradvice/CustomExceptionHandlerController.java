package com.mihai.project.library.contralleradvice;

import com.mihai.project.library.contralleradvice.exception.*;
import com.mihai.project.library.entity.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.NoResultException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NullFieldInBookDTOFoundException.class, IncorrectUserException.class})
    public ResponseEntity<String> handleResponseBindingError(Exception exc){
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IncorrectBookIdException.class, UserExistException.class, EmailAlreadyExistException.class, NoSuchUserException.class})
    public ResponseEntity<String> handleCommonException(Exception exc){
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
