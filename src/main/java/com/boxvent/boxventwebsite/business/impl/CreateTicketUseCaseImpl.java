package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.CreateTicketUseCase;
import com.boxvent.boxventwebsite.business.exception.InvalidClientException;
import com.boxvent.boxventwebsite.business.exception.InvalidCountryException;
import com.boxvent.boxventwebsite.business.exception.InvalidEventException;
import com.boxvent.boxventwebsite.business.exception.TicketAlreadyExists;
import com.boxvent.boxventwebsite.domain.CreateTicketRequest;
import com.boxvent.boxventwebsite.domain.CreateTicketResponse;
import com.boxvent.boxventwebsite.presistence.ClientRepository;
import com.boxvent.boxventwebsite.presistence.EventRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.*;
import com.boxvent.boxventwebsite.presistence.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
@AllArgsConstructor
public class CreateTicketUseCaseImpl implements CreateTicketUseCase {
    private final TicketRepository ticketRepository;
    private final ClientRepository clientRepository;
    private final EventRepository eventRepository;
    @Override
    @Transactional
    public CreateTicketResponse createTicket(CreateTicketRequest request)
    {
        ClientEntity client = clientRepository.findById(request.getClientId())
                .orElseThrow(InvalidClientException::new);
        EventEntity event = eventRepository.findById(request.getEventId())
                .orElseThrow(InvalidEventException::new);
        Example<TicketEntity> exampleLocationEntity = Example.of(TicketEntity.builder()
                .client(client)
                .event(event)
                .build());
        if(ticketRepository.exists(exampleLocationEntity))
        {
            throw new TicketAlreadyExists();
        }
        TicketEntity savedTicket = saveTicket(client,event);
        return CreateTicketResponse.builder().ticketId(savedTicket.getId()).build();
    }
    private TicketEntity saveTicket(ClientEntity client, EventEntity event)
    {
        TicketEntity ticket = TicketEntity.builder().client(client).event(event).build();
        return ticketRepository.save(ticket);
    }
}
