package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LocationAlreadyExistsException extends ResponseStatusException {
    public LocationAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "LOCATION_ALREADY_EXISTS");
    }
}
