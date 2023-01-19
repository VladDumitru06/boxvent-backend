package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SoldTicketsLimitExceeded extends ResponseStatusException {
    public SoldTicketsLimitExceeded() {
        super(HttpStatus.BAD_REQUEST, "SOLD_TICKETS_EXCEEDS_AVAILABLE_TICKETS");
    }
}
