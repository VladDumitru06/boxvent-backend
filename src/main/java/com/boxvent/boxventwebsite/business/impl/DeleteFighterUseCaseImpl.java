package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.DeleteFighterUseCase;
import com.boxvent.boxventwebsite.business.UpdateEventUseCase;
import com.boxvent.boxventwebsite.business.exception.InvalidCityException;
import com.boxvent.boxventwebsite.business.exception.InvalidEventException;
import com.boxvent.boxventwebsite.business.exception.InvalidFighterException;
import com.boxvent.boxventwebsite.business.exception.SoldTicketsLimitExceeded;
import com.boxvent.boxventwebsite.domain.Event;
import com.boxvent.boxventwebsite.domain.GetEventResponse;
import com.boxvent.boxventwebsite.domain.UpdateEventRequest;
import com.boxvent.boxventwebsite.presistence.CityRepository;
import com.boxvent.boxventwebsite.presistence.EventRepository;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.CityEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Service
@AllArgsConstructor
public class DeleteFighterUseCaseImpl implements DeleteFighterUseCase {

    private final FighterRepository fighterRepository;

    @Override
    @Transactional
    public void deleteFighter(Long fighterId) {
        FighterEntity fighterEntity = fighterRepository.findById(fighterId).orElseThrow(InvalidFighterException::new);
        fighterRepository.delete(fighterEntity);
    }
}
