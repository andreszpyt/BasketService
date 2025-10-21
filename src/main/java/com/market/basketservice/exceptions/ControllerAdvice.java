package com.market.basketservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = {DataNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleDataNotFoundException(DataNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBusinessException(BusinessException e) {
        return e.getMessage();
    }
}
