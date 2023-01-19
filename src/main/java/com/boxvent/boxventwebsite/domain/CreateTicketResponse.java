package com.boxvent.boxventwebsite.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateTicketResponse {
    private List<Long> ticketIds;
}
