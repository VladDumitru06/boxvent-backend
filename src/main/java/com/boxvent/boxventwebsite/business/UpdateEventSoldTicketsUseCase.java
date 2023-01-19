package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.Event;
import com.boxvent.boxventwebsite.domain.GetEventResponse;
import com.boxvent.boxventwebsite.domain.UpdateEventSoldTicketsRequest;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;

public interface UpdateEventSoldTicketsUseCase {
    EventEntity UpdateSoldTickets(UpdateEventSoldTicketsRequest request);
}
