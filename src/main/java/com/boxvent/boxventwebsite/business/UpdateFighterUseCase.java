package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.Event;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.domain.UpdateEventRequest;
import com.boxvent.boxventwebsite.domain.UpdateFighterRequest;

public interface UpdateFighterUseCase {
    Fighter UpdateFighter(UpdateFighterRequest request);
}
