package com.boxvent.boxventwebsite.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateFightResponse {
    private Long fightId;
}
