package com.boxvent.boxventwebsite.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFightCardRequest {
    @NotNull
    private Long challengerId;
    @NotNull
    private Long challengedId;
    @NotNull
    private Long orderNumber;
    @NotNull
    private Long rounds;
    @NotNull
    private Long eventId;
}
