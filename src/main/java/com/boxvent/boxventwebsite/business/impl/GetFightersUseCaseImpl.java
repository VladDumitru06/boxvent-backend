package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import lombok.AllArgsConstructor;
import com.boxvent.boxventwebsite.business.GetFightersUseCase;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.domain.GetAllFightersResponse;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetFightersUseCaseImpl implements GetFightersUseCase {
    private FighterRepository fighterRepository;
    private FighterConverter fighterConverter;
    @Override
    @Transactional
    public GetAllFightersResponse getFighters() {
        List<Fighter> fighters = new ArrayList<>();
        for(FighterEntity fighterEntity : fighterRepository.findAll())
        {
            fighters.add(fighterConverter.convert(fighterEntity));
        }
        return GetAllFightersResponse.builder().fighters(fighters).build();
    }
}
