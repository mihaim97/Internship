package com.mihai.project.library.contralleradvice;

import com.mihai.project.library.contralleradvice.exception.IncorrectBookIdException;
import com.mihai.project.library.contralleradvice.exception.NullFieldInBookDTOFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandlerController {

    @ExceptionHandler({NullFieldInBookDTOFoundException.class})
    public ResponseEntity<String> handleNullFieldInBookDTO(Exception exc){
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IncorrectBookIdException.class})
    public ResponseEntity<String> handleIncorrectBookIdException(Exception exc){
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
