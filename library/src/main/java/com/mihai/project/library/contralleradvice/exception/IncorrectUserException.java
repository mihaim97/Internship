package com.mihai.project.library.contralleradvice.exception;

public class IncorrectUserException extends RuntimeException {
    public IncorrectUserException(String message) {
        super(message);
    }
}
