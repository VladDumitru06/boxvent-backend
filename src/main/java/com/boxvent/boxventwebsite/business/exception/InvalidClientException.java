package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidClientException extends ResponseStatusException {
    public InvalidClientException() {
        super(HttpStatus.BAD_REQUEST, "CLIENT_INVALID");
    }
}
