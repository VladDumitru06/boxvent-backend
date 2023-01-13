package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.CreateTicketUseCase;
import com.boxvent.boxventwebsite.business.exception.InvalidClientException;
import com.boxvent.boxventwebsite.business.exception.InvalidEventException;
import com.boxvent.boxventwebsite.business.exception.TicketAlreadyExists;
import com.boxvent.boxventwebsite.domain.CreateTicketRequest;
import com.boxvent.boxventwebsite.domain.CreateTicketResponse;
import com.boxvent.boxventwebsite.presistence.EventRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.*;
import com.boxvent.boxventwebsite.presistence.TicketRepository;
import com.boxvent.boxventwebsite.presistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
@AllArgsConstructor
public class CreateTicketUseCaseImpl implements CreateTicketUseCase {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    @Override
    @Transactional
    public CreateTicketResponse createTicket(CreateTicketRequest request)
    {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(InvalidClientException::new);
        EventEntity event = eventRepository.findById(request.getEventId())
                .orElseThrow(InvalidEventException::new);
        Example<TicketEntity> exampleLocationEntity = Example.of(TicketEntity.builder()
                .user(user)
                .event(event)
                .build());

        TicketEntity savedTicket = saveTicket(user,event);
        return CreateTicketResponse.builder().ticketId(savedTicket.getId()).build();
    }
    private TicketEntity saveTicket(UserEntity user, EventEntity event)
    {
        TicketEntity ticket = TicketEntity.builder().user(user).event(event).build();
        System.out.println(ticket.getEvent().getId() + " " + ticket.getUser().getId());
        return ticketRepository.save(ticket);
    }
}
