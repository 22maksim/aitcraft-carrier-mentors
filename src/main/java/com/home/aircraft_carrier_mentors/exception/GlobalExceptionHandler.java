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
        return e.getMessage() + " Ваш запрос не верный";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MyJsonSerializationExc.class)
    public String jsonSerializationException(MyJsonSerializationExc e) {
        return e.getMessage() + " Возникли проблемы с сериализацией, проверьте данные и попробуйте снова";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MyNullPointerException.class)
    public String myNullPointerException(MyNullPointerException e) {
        return e.getMessage() + " Проверьте корректность данных, данные не должны быть пустыми";
    }
}
