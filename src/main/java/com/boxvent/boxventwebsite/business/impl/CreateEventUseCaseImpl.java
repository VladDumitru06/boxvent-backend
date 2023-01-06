package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.CreateEventUseCase;
import com.boxvent.boxventwebsite.business.CreateFightCardUseCase;
import com.boxvent.boxventwebsite.business.exception.*;
import com.boxvent.boxventwebsite.domain.CreateEventRequest;
import com.boxvent.boxventwebsite.domain.CreateEventResponse;
import com.boxvent.boxventwebsite.domain.CreateFightCardRequest;
import com.boxvent.boxventwebsite.domain.CreateFightCardResponse;
import com.boxvent.boxventwebsite.presistence.*;
import com.boxvent.boxventwebsite.presistence.Impl.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CreateEventUseCaseImpl implements CreateEventUseCase, CreateFightCardUseCase {
    private final EventRepository eventRepository;
    private final CityRepository cityRepository;
    private final FighterRepository fighterRepository;
    private final FightCardRepository fightCardRepository;
    private final FightRepository fightRepository;
    @Override
    @Transactional
    public CreateEventResponse createEvent(CreateEventRequest request)
    {
        if(eventRepository.existsByEventName(request.getEventName()))
        {
            throw new EventNameAlreadyExistsException();
        }

        EventEntity savedEvent = saveEvent(request);
        if(!request.getFightCards().isEmpty())
        {
            for (CreateFightCardRequest fightCard : request.getFightCards()) {
                fightCard.setEventId(savedEvent.getId());
                createFightCard(fightCard);
            }
        }
        return CreateEventResponse.builder().eventId(savedEvent.getId()).build();
    }
    private EventEntity saveEvent(CreateEventRequest request)
    {
        System.out.println(request.getEventDate() + "EVENT DATE");
        CityEntity city = cityRepository.findByCityName(request.getCityName())
                .orElseThrow(InvalidCityException::new);
        EventEntity newEvent = EventEntity.builder()
                .eventName(request.getEventName())
                .availableTickets(request.getAvailableTickets())
                .city(city)
                .ticketPrice(request.getTicketPrice())
                .dateTime(request.getEventDate())
                .soldTickets(0L)
                .build();
        return eventRepository.save(newEvent);
    }

    @Override
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
        FightCardEntity newFightCard = FightCardEntity.builder().fight(savedFight).event(event).order_nr(request.getOrderNumber()).build();
        return fightCardRepository.save(newFightCard);
    }
}
