package com.boxvent.boxventwebsite.presistence.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FighterEntity {
    private Long id;
    private String name;
    private String record;
}
