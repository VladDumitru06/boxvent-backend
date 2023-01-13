package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.Ticket;
import com.boxvent.boxventwebsite.domain.User;
import com.boxvent.boxventwebsite.presistence.Impl.entity.TicketEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Data
final class TicketConverter {
    public Ticket convert(TicketEntity ticketEntity) {
        return Ticket.builder()
                .id(ticketEntity.getId())
                .eventId(ticketEntity.getEvent().getId())
                .userId(ticketEntity.getUser().getId())
                .build();
    }

}