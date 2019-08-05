package com.lteixeira.apicustomers.exception;

public class PurchaseOrderException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public PurchaseOrderException(String message){
        super(message);
    }
}
