package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCityException extends ResponseStatusException {
    public InvalidCityException() {
        super(HttpStatus.BAD_REQUEST, "CITY_INVALID");
    }
}
