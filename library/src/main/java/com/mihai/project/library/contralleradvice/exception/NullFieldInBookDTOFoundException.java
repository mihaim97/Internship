package com.mihai.project.library.contralleradvice.exception;

public class NullFieldInBookDTOFoundException extends RuntimeException {
    public NullFieldInBookDTOFoundException(String message){
        super(message);
    }
}
