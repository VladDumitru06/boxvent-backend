package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.GetEventsUseCase;
import com.boxvent.boxventwebsite.business.GetTicketsUseCase;
import com.boxvent.boxventwebsite.domain.Event;
import com.boxvent.boxventwebsite.domain.GetAllEventsResponse;
import com.boxvent.boxventwebsite.domain.GetTicketsByUserResponse;
import com.boxvent.boxventwebsite.domain.Ticket;
import com.boxvent.boxventwebsite.presistence.EventRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.TicketEntity;
import com.boxvent.boxventwebsite.presistence.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetTicketsUseCaseImpl implements GetTicketsUseCase {
    private TicketRepository ticketRepository;
    private TicketConverter ticketConverter ;
    @Override
    @Transactional
    public GetTicketsByUserResponse getTickets(Long id) {
        List<Ticket> tickets = new ArrayList<>();
        for(TicketEntity ticketEntity : ticketRepository.findByUserId(id))
        {
            tickets.add(ticketConverter.convert(ticketEntity));
        }
        return GetTicketsByUserResponse.builder().tickets(tickets).build();
    }
}
