package com.ufpr.tads.web2.exceptions;

public class ProdutoException extends AppException{

    public ProdutoException() {
    }

    public ProdutoException(String message) {
        super(message);
    }

    public ProdutoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
