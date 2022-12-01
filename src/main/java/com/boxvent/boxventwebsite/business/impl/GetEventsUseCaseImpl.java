package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.GetEventsUseCase;
import com.boxvent.boxventwebsite.business.GetFightersUseCase;
import com.boxvent.boxventwebsite.domain.Event;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.domain.GetAllEventsResponse;
import com.boxvent.boxventwebsite.domain.GetAllFightersResponse;
import com.boxvent.boxventwebsite.presistence.EventRepository;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetEventsUseCaseImpl implements GetEventsUseCase {
    private EventRepository eventRepository;
    private EventConverter eventConverter ;
    @Override
    @Transactional
    public GetAllEventsResponse getEvents() {
        List<Event> events = new ArrayList<>();
        for(EventEntity eventEntity : eventRepository.findAll())
        {
            events.add(eventConverter.convert(eventEntity));
        }
        return GetAllEventsResponse.builder().events(events).build();
    }
}
