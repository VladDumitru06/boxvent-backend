package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EventNameAlreadyExistsException extends ResponseStatusException {
    public EventNameAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "EVENT_NAME_ALREADY_EXISTS");
    }
}
