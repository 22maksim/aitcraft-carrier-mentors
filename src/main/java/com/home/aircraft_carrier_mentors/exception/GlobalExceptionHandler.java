package com.home.aircraft_carrier_mentors.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MyNotFoundException.class)
    public String entityNotFoundException(MyNotFoundException e) {
        return e.getMessage() + " This is my HANDLER";
    }
}
