package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.GetAllEventsResponse;
import com.boxvent.boxventwebsite.domain.GetEventRequest;
import com.boxvent.boxventwebsite.domain.GetEventResponse;

public interface GetEventUseCase {
    GetEventResponse getEvent(Long eventId);
}
