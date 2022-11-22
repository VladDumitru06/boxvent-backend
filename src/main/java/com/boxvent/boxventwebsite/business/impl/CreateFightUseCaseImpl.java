package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.CreateFightUseCase;
import com.boxvent.boxventwebsite.business.exception.FightAlreadyExists;
import com.boxvent.boxventwebsite.business.exception.FightCardAlreadyExists;
import com.boxvent.boxventwebsite.business.exception.InvalidFighterException;
import com.boxvent.boxventwebsite.domain.CreateFightCardResponse;
import com.boxvent.boxventwebsite.domain.CreateFightRequest;
import com.boxvent.boxventwebsite.domain.CreateFightResponse;
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
@Service
@AllArgsConstructor
public class CreateFightUseCaseImpl implements CreateFightUseCase {
    private final FightRepository fightRepository;
    private final FighterRepository fighterRepository;
    @Override
    @Transactional
    public CreateFightResponse createFight(CreateFightRequest request)
    {
        FighterEntity challenger = fighterRepository.findById(request.getChallengerId())
                .orElseThrow(InvalidFighterException::new);
        FighterEntity challenged = fighterRepository.findById(request.getChallengedId())
                .orElseThrow(InvalidFighterException::new);
        Example<FightEntity> exampleFightEntity = Example.of(FightEntity.builder()
                .challengerFighter(challenger)
                .challengedFighter(challenged)
                .rounds(request.getRounds())
                .build());

        if(fightRepository.exists(exampleFightEntity))
        {
            throw new FightAlreadyExists();
        }

        FightEntity savedFight = saveFight(request,challenger,challenged);

        return CreateFightResponse.builder().fightId(savedFight.getId()).build();
    }
    private FightEntity saveFight(CreateFightRequest request, FighterEntity challenger, FighterEntity challenged)
    {
        FightEntity newFighter = FightEntity.builder()
                .challengerFighter(challenger)
                .challengedFighter(challenged)
                .rounds(request.getRounds())
                .build();
        return fightRepository.save(newFighter);
    }
}
