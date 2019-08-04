package com.lteixeira.apiproducts.exception;

public class ProductException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public ProductException(String message){
        super(message);
    }
}
