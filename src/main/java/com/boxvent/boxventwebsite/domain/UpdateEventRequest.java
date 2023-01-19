package com.boxvent.boxventwebsite.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventRequest {

    @NotNull
    private Long eventId;
    private String eventName;
    private String description;
    private String cityName;
    private Long availableTickets;
    private Double ticketPrice;
    private LocalDateTime dateTime;
    private String image;
}
