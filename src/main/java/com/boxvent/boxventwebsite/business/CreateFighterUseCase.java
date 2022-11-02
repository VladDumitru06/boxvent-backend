package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.CreateFighterRequest;
import com.boxvent.boxventwebsite.domain.CreateFighterResponse;

public interface CreateFighterUseCase {
    CreateFighterResponse createFighter(CreateFighterRequest request);
 }
