package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidFighterException extends ResponseStatusException {
    public InvalidFighterException() {
        super(HttpStatus.BAD_REQUEST, "FIGHTER_INVALID");
    }
}
