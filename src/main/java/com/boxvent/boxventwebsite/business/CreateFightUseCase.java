package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.CreateFightRequest;
import com.boxvent.boxventwebsite.domain.CreateFightResponse;

public interface CreateFightUseCase {
    CreateFightResponse createFight(CreateFightRequest request);
}
