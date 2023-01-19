package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.Event;
import com.boxvent.boxventwebsite.domain.UpdateEventRequest;
import com.boxvent.boxventwebsite.domain.UpdateEventSoldTicketsRequest;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;

public interface UpdateEventUseCase {
    Event UpdateEvent(UpdateEventRequest request);
}
