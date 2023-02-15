package com.ufpr.tads.web2.exceptions;

public class LolException extends Exception{

    public LolException() {
    }

    public LolException(String message) {
        super(message);
    }

    public LolException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
