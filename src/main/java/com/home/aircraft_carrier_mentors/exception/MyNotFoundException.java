package com.home.aircraft_carrier_mentors.exception;

import jakarta.persistence.EntityNotFoundException;

public class MyNotFoundException extends EntityNotFoundException {
    public MyNotFoundException(String message) {
        super(message);
    }
}
