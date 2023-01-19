package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.DeleteEventUseCase;
import com.boxvent.boxventwebsite.business.DeleteFighterUseCase;
import com.boxvent.boxventwebsite.business.exception.InvalidFighterException;
import com.boxvent.boxventwebsite.presistence.EventRepository;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class DeleteEventUseCaseImpl implements DeleteEventUseCase {

    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void deleteEvent(Long eventId) {
        EventEntity eventEntity = eventRepository.findById(eventId).orElseThrow(InvalidFighterException::new);
        eventRepository.delete(eventEntity);
    }
}
