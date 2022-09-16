package com.boxvent.boxventwebsite.business.impl;

import lombok.AllArgsConstructor;
import com.boxvent.boxventwebsite.business.CreateFighterUseCase;
import com.boxvent.boxventwebsite.business.exception.FighterNameAlreadyExistsException;
import com.boxvent.boxventwebsite.domain.CreateFighterRequest;
import com.boxvent.boxventwebsite.domain.CreateFighterResponse;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import com.boxvent.boxventwebsite.presistence.entity.FighterEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateFighterUseCaseImpl implements CreateFighterUseCase {
    private final FighterRepository fighterRepository;
    @Override
    public CreateFighterResponse createFighter(CreateFighterRequest request) {
        if(fighterRepository.existByName(request.getName())){
            throw new FighterNameAlreadyExistsException();
        }
        FighterEntity savedFighter = saveNewFighter(request);
        return CreateFighterResponse.builder().fighterId(savedFighter.getId()).build();
    }
    private FighterEntity saveNewFighter(CreateFighterRequest request) {
        FighterEntity newFighter = FighterEntity.builder()
                .name(request.getName())
                .record(request.getRecord())
                .build();
        return fighterRepository.add(newFighter);
    }
}
