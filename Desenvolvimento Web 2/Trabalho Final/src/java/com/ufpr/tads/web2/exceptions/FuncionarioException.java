package com.ufpr.tads.web2.exceptions;

public class FuncionarioException extends LolException {

    public FuncionarioException() {
    }

    public FuncionarioException(String message) {
        super(message);
    }

    public FuncionarioException(String message, Throwable cause) {
        super(message, cause);
    }

}
