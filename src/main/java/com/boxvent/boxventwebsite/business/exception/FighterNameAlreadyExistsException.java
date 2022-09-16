package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FighterNameAlreadyExistsException extends ResponseStatusException {
    public FighterNameAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "FIGHTER_NAME_ALREADY_EXISTS");
    }
}
