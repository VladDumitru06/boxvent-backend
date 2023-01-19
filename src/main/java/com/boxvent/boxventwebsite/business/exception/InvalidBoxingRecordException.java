package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidBoxingRecordException extends ResponseStatusException {
    public InvalidBoxingRecordException() {
        super(HttpStatus.BAD_REQUEST, "BOXING_RECORD_INVALID");
    }
}
