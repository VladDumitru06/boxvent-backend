package com.boxvent.boxventwebsite.domain;

import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fight {
    private Long id;
    private Fighter challengerFighter;
    private Fighter challengedFighter;
    private Long rounds;
}
