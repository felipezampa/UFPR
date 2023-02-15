package com.ufpr.tads.web2.exceptions;

public class ClientesException extends Exception{

    public ClientesException() {
    }

    public ClientesException(String message) {
        super(message);
    }

    public ClientesException(String message, Throwable cause) {
        super(message, cause);
    }
   
}
