package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.Event;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
final class EventConverter {
    private final LocationConverter locationConverter;
    public Event convert(EventEntity event) {
        return Event.builder()
                .id(event.getId())
                .name(event.getEventName())
                .available_tickets(event.getAvailableTickets())
                .sold_tickets(event.getSoldTickets())
                .location(locationConverter.convert(event.getLocation()))
                .date(event.getDateTime())
                .build();
    }

}
