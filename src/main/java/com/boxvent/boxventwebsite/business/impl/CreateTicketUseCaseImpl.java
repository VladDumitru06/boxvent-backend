package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.CreateTicketUseCase;
import com.boxvent.boxventwebsite.business.UpdateEventSoldTicketsUseCase;
import com.boxvent.boxventwebsite.business.exception.AvailableTicketsLimitExceeded;
import com.boxvent.boxventwebsite.business.exception.InvalidClientException;
import com.boxvent.boxventwebsite.business.exception.InvalidEventException;
import com.boxvent.boxventwebsite.business.exception.TicketAlreadyExists;
import com.boxvent.boxventwebsite.domain.CreateTicketRequest;
import com.boxvent.boxventwebsite.domain.CreateTicketResponse;
import com.boxvent.boxventwebsite.domain.Event;
import com.boxvent.boxventwebsite.domain.UpdateEventSoldTicketsRequest;
import com.boxvent.boxventwebsite.presistence.EventRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.*;
import com.boxvent.boxventwebsite.presistence.TicketRepository;
import com.boxvent.boxventwebsite.presistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreateTicketUseCaseImpl implements CreateTicketUseCase {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final EventConverter eventConverter;
    private final UpdateEventSoldTicketsUseCase updateEventSoldTicketsUseCase;
    @Override
    @Transactional
    public CreateTicketResponse createTicket(CreateTicketRequest request)
    {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(InvalidClientException::new);
        EventEntity event = eventRepository.findById(request.getEventId())
                .orElseThrow(InvalidEventException::new);


        List<TicketEntity> savedTickets = saveTicket(user,event,request.getNumberOfTickets());
        List<Long> ticketIds = savedTickets.stream().map(TicketEntity::getId).toList();
        return CreateTicketResponse.builder().ticketIds(ticketIds).build();
    }
    private List<TicketEntity> saveTicket(UserEntity user, EventEntity event,Long numberOfTickets)
    {
        List<TicketEntity> tickets = new ArrayList<>();
        EventEntity updatedEvent = UpdateSoldTickets(UpdateEventSoldTicketsRequest.builder().eventId(event.getId()).soldTickets(numberOfTickets).build());

        for(int i = 0 ; i < numberOfTickets; i++)
        {
            TicketEntity newTicket = TicketEntity.builder().user(user).event(updatedEvent).build();
            tickets.add(newTicket);
        }

        return ticketRepository.saveAll(tickets);
    }
    public EventEntity UpdateSoldTickets(UpdateEventSoldTicketsRequest request) {
        System.out.println(request.getEventId());
        EventEntity eventEntity = eventRepository.findById(request.getEventId())
                .orElseThrow(InvalidEventException::new);
        Long currentSoldTickets = eventEntity.getSoldTickets();
        if(request.getSoldTickets() + currentSoldTickets <= eventEntity.getAvailableTickets())
            eventEntity.setSoldTickets(currentSoldTickets + request.getSoldTickets());
        else
            throw new AvailableTicketsLimitExceeded();
        return eventRepository.save(eventEntity);
    }

}
