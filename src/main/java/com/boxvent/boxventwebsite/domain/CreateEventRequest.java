package com.boxvent.boxventwebsite.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequest {

    @NotBlank
    private String eventName;
    @NotBlank
    private String cityName;
    @NotNull
    private Long availableTickets;
    @NotNull
    private LocalDateTime eventDate;
    @NotNull
    private Double ticketPrice;
    @NotNull
    private Set<CreateFightCardRequest> fightCards;
}
