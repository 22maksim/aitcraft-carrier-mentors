package com.home.aircraft_carrier_mentors.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class MyJsonSerializationExc extends RuntimeException {
    public MyJsonSerializationExc(String message) {
        super(message);
    }
}
