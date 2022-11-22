package com.boxvent.boxventwebsite.domain;

import com.boxvent.boxventwebsite.presistence.Impl.entity.FightCardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequest {

    @NotBlank
    private String eventName;
    @NotNull
    private Long locationId;
    @NotNull
    private Long availableTickets;

    @NotNull
    private Double ticketPrice;
}
