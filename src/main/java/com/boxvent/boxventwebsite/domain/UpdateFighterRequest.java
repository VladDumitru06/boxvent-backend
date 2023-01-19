package com.boxvent.boxventwebsite.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFighterRequest {

    @NotNull
    private Long fighterId;
    private String fighterName;
    private Long wins;
    private Long draws;
    private Long loses;
    private String image;
}
