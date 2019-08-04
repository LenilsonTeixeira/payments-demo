package com.lteixeira.apicustomers.exception;

public class CustomerException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public CustomerException(String message){
        super(message);
    }
}
