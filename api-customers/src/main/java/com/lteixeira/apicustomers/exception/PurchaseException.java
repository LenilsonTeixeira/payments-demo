package com.lteixeira.apicustomers.exception;

public class PurchaseException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public PurchaseException(String message){
        super(message);
    }
}
