package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FightAlreadyExists extends ResponseStatusException {
    public FightAlreadyExists() {
        super(HttpStatus.BAD_REQUEST, "FIGHT_ALREADY_EXISTS");
    }
}
