package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.GetEventUseCase;
import com.boxvent.boxventwebsite.domain.Event;
import com.boxvent.boxventwebsite.domain.GetAllEventsResponse;
import com.boxvent.boxventwebsite.domain.GetEventRequest;
import com.boxvent.boxventwebsite.domain.GetEventResponse;
import com.boxvent.boxventwebsite.presistence.EventRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetEventUseCaseImpl implements GetEventUseCase {
    private EventRepository eventRepository;
    private EventConverter eventConverter ;
    @Override
    @Transactional
    public GetEventResponse getEvent(Long eventId) {
        EventEntity requestedEvent = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("EVENT_NOT_FOUND"));
        return GetEventResponse.builder().event(eventConverter.convert(requestedEvent)).build();
    }
}
