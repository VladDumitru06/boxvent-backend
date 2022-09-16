package com.boxvent.boxventwebsite.business.impl;

import lombok.AllArgsConstructor;
import com.boxvent.boxventwebsite.business.GetFightersUseCase;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.domain.GetAllFightersResponse;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetFightersUseCaseImpl implements GetFightersUseCase {
    private FighterRepository fighterRepository;
    @Override
    public GetAllFightersResponse getFighters() {
        List<Fighter> fighters = fighterRepository.getAll()
                .stream()
                .map(FighterConverter::convert)
                .toList();
        return GetAllFightersResponse.builder().fighters(fighters).build();
    }
}
