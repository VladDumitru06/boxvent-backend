package com.boxvent.boxventwebsite.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoxingRecord {
    private Fighter fighter;
    private Long wins;
    private Long loses;
    private Long draws;
}
