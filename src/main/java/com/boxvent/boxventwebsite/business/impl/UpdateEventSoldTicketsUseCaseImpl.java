package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.UpdateEventSoldTicketsUseCase;
import com.boxvent.boxventwebsite.business.exception.AvailableTicketsLimitExceeded;
import com.boxvent.boxventwebsite.business.exception.InvalidClientException;
import com.boxvent.boxventwebsite.business.exception.InvalidCountryException;
import com.boxvent.boxventwebsite.business.exception.InvalidEventException;
import com.boxvent.boxventwebsite.domain.*;
import com.boxvent.boxventwebsite.presistence.EventRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.TicketEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.UserEntity;
import com.boxvent.boxventwebsite.presistence.TicketRepository;
import com.boxvent.boxventwebsite.presistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.naming.LimitExceededException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UpdateEventSoldTicketsUseCaseImpl implements UpdateEventSoldTicketsUseCase {

    private final EventRepository eventRepository;
    private final EventConverter eventConverter;
    @Override
    @Transactional
    public EventEntity UpdateSoldTickets(UpdateEventSoldTicketsRequest request) {
        EventEntity eventEntity = eventRepository.findById(request.getEventId())
                .orElseThrow(InvalidEventException::new);
        Long currentSoldTickets = eventEntity.getSoldTickets();
        if(eventEntity.getSoldTickets() + currentSoldTickets <= eventEntity.getAvailableTickets())
            eventEntity.setSoldTickets(currentSoldTickets + request.getSoldTickets());
        else
            throw new AvailableTicketsLimitExceeded();
        return eventRepository.save(eventEntity);
    }
}
