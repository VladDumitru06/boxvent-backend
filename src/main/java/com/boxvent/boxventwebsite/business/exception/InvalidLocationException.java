package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidLocationException extends ResponseStatusException {
    public InvalidLocationException() {
        super(HttpStatus.BAD_REQUEST, "LOCATION_INVALID");
    }
}
