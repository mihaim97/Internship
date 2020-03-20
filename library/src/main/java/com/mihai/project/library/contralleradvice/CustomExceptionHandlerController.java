package com.mihai.project.library.contralleradvice;

import com.mihai.project.library.contralleradvice.exception.*;
import com.mihai.project.library.util.message.ExceptionMessage;
import com.mihai.project.library.util.message.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageBuilder messageBuilder;

    @ExceptionHandler({ResultBindingValidationException.class})
    public ResponseEntity<String> handleResponseBindingError(Exception exc){
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BookRentOrRequestException.class})
    public ResponseEntity<String> noCopyAvailable(Exception exc){
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> failToDeleteResource(){
        String message = messageBuilder.asJSON(ExceptionMessage.INTEGRITY_VIOLATION);
        return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
    }


}
