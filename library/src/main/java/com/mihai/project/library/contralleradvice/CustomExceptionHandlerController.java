package com.mihai.project.library.contralleradvice;

import com.mihai.project.library.contralleradvice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResultBindingValidationException.class})
    public ResponseEntity<String> handleResponseBindingError(Exception exc){
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BookRentOrRequestException.class})
    public ResponseEntity<String> noCopyAvailable(Exception exc){
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
    }


}
