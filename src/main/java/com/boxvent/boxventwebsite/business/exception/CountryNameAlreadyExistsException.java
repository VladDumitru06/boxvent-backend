package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CountryNameAlreadyExistsException extends ResponseStatusException {
    public CountryNameAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "COUNTRY_NAME_ALREADY_EXISTS");
    }
}
