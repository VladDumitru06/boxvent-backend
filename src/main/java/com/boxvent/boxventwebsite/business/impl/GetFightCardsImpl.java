package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.GetFightCardsUseCase;
import com.boxvent.boxventwebsite.business.exception.InvalidEventException;
import com.boxvent.boxventwebsite.domain.FightCard;
import com.boxvent.boxventwebsite.domain.GetFightCardsByEventResponse;
import com.boxvent.boxventwebsite.presistence.FightCardRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FightCardEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetFightCardsImpl implements GetFightCardsUseCase {
    private FightCardRepository fightCardRepository;
    private FightCardConverter fightCardConverter;

    @Transactional
    @Override
    public GetFightCardsByEventResponse getFightCards(Long eventId) {
        List<FightCard> fightCards = new ArrayList<>();
        for(FightCardEntity fightCardEntity : fightCardRepository.findByEventId(eventId)
                .orElseThrow(InvalidEventException::new))
        {
            fightCards.add(fightCardConverter.convert(fightCardEntity));
        }
        return GetFightCardsByEventResponse.builder().fightCards(fightCards).build();
    }
}
