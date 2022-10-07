package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.GetFighterUseCase;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetFighterUseCaseImpl implements GetFighterUseCase {
    private FighterRepository fighterRepository;

    @Override
    public Fighter getFighter(long id) {
        FighterEntity fighterEntity = fighterRepository.findById(id);
        if(fighterEntity == null)
            return null;
        return FighterConverter.convert(fighterEntity);
    }
}
