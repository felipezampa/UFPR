package com.ufpr.tads.web2.exceptions;

public class AtendimentoException extends AppException{

    public AtendimentoException() {
    }

    public AtendimentoException(String message) {
        super(message);
    }

    public AtendimentoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
