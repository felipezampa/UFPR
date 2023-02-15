package com.ufpr.tads.web2.exceptions;

public class LoginException extends AppException{

    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }
   
}

