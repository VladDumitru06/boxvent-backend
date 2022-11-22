package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TicketAlreadyExists extends ResponseStatusException {
    public TicketAlreadyExists() {
        super(HttpStatus.BAD_REQUEST, "TICKET_ALREADY_EXISTS");
    }
}
