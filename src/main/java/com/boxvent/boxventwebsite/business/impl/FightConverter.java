package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.Event;
import com.boxvent.boxventwebsite.domain.Fight;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FightEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
final class FightConverter {
    private final FighterConverter fighterConverter;
    public Fight convert(FightEntity fightEntity) {
        return Fight.builder()
                .id(fightEntity.getId())
                .challengedFighter(fighterConverter.convert(fightEntity.getChallengedFighter()))
                .challengerFighter(fighterConverter.convert(fightEntity.getChallengerFighter()))
                .rounds(fightEntity.getRounds())
                .build();
    }

}
