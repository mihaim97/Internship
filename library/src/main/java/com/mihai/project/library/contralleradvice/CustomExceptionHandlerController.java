package com.mihai.project.library.contralleradvice;

import com.mihai.project.library.contralleradvice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandlerController {

    @ExceptionHandler({NullFieldInBookDTOFoundException.class, IncorrectUserException.class})
    public ResponseEntity<String> handleResponseBindingError(Exception exc){
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IncorrectBookIdException.class, UserExistException.class, EmailAlreadyExistException.class, NoSuchUserException.class})
    public ResponseEntity<String> handleCommonException(Exception exc){
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
