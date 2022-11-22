package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.CreateEventRequest;
import com.boxvent.boxventwebsite.domain.CreateEventResponse;

public interface CreateEventUseCase {
    CreateEventResponse createEvent(CreateEventRequest request);
}
