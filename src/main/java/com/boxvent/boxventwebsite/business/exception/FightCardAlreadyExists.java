package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FightCardAlreadyExists extends ResponseStatusException {
    public FightCardAlreadyExists() {
        super(HttpStatus.BAD_REQUEST, "FIGHT_CARD_ALREADY_EXISTS");
    }
}
