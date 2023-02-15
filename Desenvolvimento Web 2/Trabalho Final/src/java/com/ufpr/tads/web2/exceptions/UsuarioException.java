package com.ufpr.tads.web2.exceptions;

public class UsuarioException extends LolException{

    public UsuarioException() {
    }

    public UsuarioException(String message) {
        super(message);
    }

    public UsuarioException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
