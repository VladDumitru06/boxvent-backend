package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CountryCodeAlreadyExistsException extends ResponseStatusException {
    public CountryCodeAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "COUNTRY_CODE_ALREADY_EXISTS");
    }
}
