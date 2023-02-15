package com.ufpr.tads.web2.exceptions;

public class AppException extends Exception {

    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
