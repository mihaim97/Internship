package com.mihai.project.library.contralleradvice.exception;

public class BookRentOrRequestException extends RuntimeException {
    public BookRentOrRequestException(String message) {
        super(message);
    }
}
