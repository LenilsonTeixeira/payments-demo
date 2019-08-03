package com.lteixeira.msorders.exception;

public class OrderInfoException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public OrderInfoException(String message){
        super(message);
    }

    public OrderInfoException(String message , Throwable cause){
        super(message,cause);
    }
}
