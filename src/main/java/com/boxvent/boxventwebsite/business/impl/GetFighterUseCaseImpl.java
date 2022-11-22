package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.GetFighterUseCase;
import com.boxvent.boxventwebsite.business.exception.InvalidFighterException;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetFighterUseCaseImpl implements GetFighterUseCase {
    private FighterRepository fighterRepository;
    private FighterConverter fighterConverter;

    @Override
    @Transactional
    public Fighter getFighter(long id) {
        FighterEntity fighterEntity = fighterRepository.findById(id)
                .orElseThrow(InvalidFighterException::new);
        return fighterConverter.convert(fighterEntity);
    }
}
