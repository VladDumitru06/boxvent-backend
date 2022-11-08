package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.presistence.BoxingRecordRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.BoxingRecordEntity;
import lombok.AllArgsConstructor;
import com.boxvent.boxventwebsite.business.CreateFighterUseCase;
import com.boxvent.boxventwebsite.business.exception.FighterNameAlreadyExistsException;
import com.boxvent.boxventwebsite.domain.CreateFighterRequest;
import com.boxvent.boxventwebsite.domain.CreateFighterResponse;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CreateFighterUseCaseImpl implements CreateFighterUseCase {
    private final FighterRepository fighterRepository;
    private final BoxingRecordRepository boxingRecordRepository;
    @Override
    @Transactional
    public CreateFighterResponse createFighter(CreateFighterRequest request) {
        if(fighterRepository.existsByName(request.getName())){
            throw new FighterNameAlreadyExistsException();
        }
        FighterEntity savedFighter = saveNewFighter(request);
        return CreateFighterResponse.builder().fighterId(savedFighter.getId()).build();
    }
    private FighterEntity saveNewFighter(CreateFighterRequest request) {
        FighterEntity newFighter = FighterEntity.builder()
                .name(request.getName())
                .build();
        FighterEntity savedFighter =fighterRepository.save(newFighter);
        saveNewRecord(request.getWins(), request.getDraws(), request.getLoses(), newFighter);
        return  savedFighter;
    }
    private BoxingRecordEntity saveNewRecord(Long wins,Long draws,Long loses,FighterEntity fighter)
    {
        BoxingRecordEntity newRecord = BoxingRecordEntity.builder()
                .wins(wins)
                .draws(draws)
                .loses(loses)
                .fighter(fighter)
                .build();
        return boxingRecordRepository.save(newRecord);
    }
}
