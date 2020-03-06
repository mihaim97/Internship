package com.mihai.project.library.contralleradvice.exception;

public class NoCopyStockAvailableException extends RuntimeException {
    public NoCopyStockAvailableException(String message) {
        super(message);
    }
}
