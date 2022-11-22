package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.CreateTicketRequest;
import com.boxvent.boxventwebsite.domain.CreateTicketResponse;

public interface CreateTicketUseCase {
    CreateTicketResponse createTicket (CreateTicketRequest request);
}
