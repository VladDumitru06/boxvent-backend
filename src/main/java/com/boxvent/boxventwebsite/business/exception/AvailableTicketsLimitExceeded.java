package com.boxvent.boxventwebsite.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AvailableTicketsLimitExceeded extends ResponseStatusException {
    public AvailableTicketsLimitExceeded() {
        super(HttpStatus.BAD_REQUEST, "BOUGHT_TICKETS_EXCEEDS_AVAILABLE_TICKETS");
    }
}
