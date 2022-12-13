package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.Event;
import com.boxvent.boxventwebsite.domain.FightCard;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FightCardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
final class FightCardConverter {
    private EventConverter eventConverter;
    private FightConverter fightConverter;
    public FightCard convert(FightCardEntity fightCardEntity) {
        return FightCard.builder()
                .id(fightCardEntity.getId())
                .event(eventConverter.convert(fightCardEntity.getEvent()))
                .fight(fightConverter.convert(fightCardEntity.getFight()))
                .order_nr(fightCardEntity.getOrder_nr())
                .build();
    }

}
