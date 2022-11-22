package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.CreateFightCardUseCase;
import com.boxvent.boxventwebsite.business.exception.FightCardAlreadyExists;
import com.boxvent.boxventwebsite.business.exception.InvalidFighterException;
import com.boxvent.boxventwebsite.domain.CreateFightCardRequest;
import com.boxvent.boxventwebsite.domain.CreateFightCardResponse;
import com.boxvent.boxventwebsite.presistence.EventRepository;
import com.boxvent.boxventwebsite.presistence.FightCardRepository;
import com.boxvent.boxventwebsite.presistence.FightRepository;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FightCardEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FightEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateFightCardUseCaseImpl {
    private final FightCardRepository fightCardRepository;
    private final FighterRepository fighterRepository;
    private final EventRepository eventRepository;
    private final FightRepository fightRepository;

    @Transactional
    public CreateFightCardResponse createFightCard(CreateFightCardRequest request)
    {
        FighterEntity challenger = fighterRepository.findById(request.getChallengerId())
                .orElseThrow(InvalidFighterException::new);
        FighterEntity challenged = fighterRepository.findById(request.getChallengedId())
                .orElseThrow(InvalidFighterException::new);
        EventEntity event = eventRepository.findById(request.getEventId())
                .orElseThrow(InvalidFighterException::new);
        FightEntity exampleFightEntity = FightEntity.builder().challengedFighter(challenger).challengedFighter(challenged).rounds(request.getRounds()).build();

        Example<FightCardEntity> example = Example.of(FightCardEntity.builder().fight(exampleFightEntity).event(event).build());

        if(fightCardRepository.exists(example))
        {
            throw new FightCardAlreadyExists();
        }
        FightCardEntity savedFightCard = saveFightCard(request,challenger,challenged,event);
        return CreateFightCardResponse.builder().fightCardId(savedFightCard.getId()).build();
    }
    private FightCardEntity saveFightCard(CreateFightCardRequest request,FighterEntity challenger,FighterEntity challenged,EventEntity event)
    {
        FightEntity fightEntity = FightEntity.builder().challengerFighter(challenger).challengedFighter(challenged).rounds(request.getRounds()).build();
        FightEntity savedFight = fightRepository.save(fightEntity);
        FightCardEntity newFightCard = FightCardEntity.builder().fight(savedFight).event(event).build();
        return fightCardRepository.save(newFightCard);
    }
}
