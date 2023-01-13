package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.GetAllCountriesResponse;
import com.boxvent.boxventwebsite.domain.GetTicketsByUserResponse;

public interface GetTicketsUseCase {
    GetTicketsByUserResponse getTickets(Long userId);
}
