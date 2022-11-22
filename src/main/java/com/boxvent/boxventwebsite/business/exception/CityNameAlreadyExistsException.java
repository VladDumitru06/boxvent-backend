package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CityNameAlreadyExistsException extends ResponseStatusException {
    public CityNameAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "CITY_NAME_ALREADY_EXISTS");
    }
}
