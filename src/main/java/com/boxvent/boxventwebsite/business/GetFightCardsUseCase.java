package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.GetFightCardsByEventResponse;

public interface GetFightCardsUseCase {
    GetFightCardsByEventResponse getFightCards(Long eventId);
}
