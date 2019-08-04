package com.lteixeira.apicustomers.handler;

import com.lteixeira.apicustomers.exception.CustomerException;
import com.lteixeira.apicustomers.exception.JsonParseException;
import com.lteixeira.apicustomers.exception.NotFoundException;
import com.lteixeira.apicustomers.model.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDetail> handleNullPointerException(NullPointerException e, final HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDetail(500, e.getMessage(), Instant.now().toEpochMilli(), request.getRequestURI()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetail> handleNotFoundException(NotFoundException e, final HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetail(404, e.getMessage(), Instant.now().toEpochMilli(), request.getRequestURI()));
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorDetail> handleJsonParseException(JsonParseException e, final HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDetail(500, e.getMessage(), Instant.now().toEpochMilli(), request.getRequestURI()));
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorDetail> handleCustomerException(NotFoundException e, final HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDetail(500, e.getMessage(), Instant.now().toEpochMilli(), request.getRequestURI()));
    }

}