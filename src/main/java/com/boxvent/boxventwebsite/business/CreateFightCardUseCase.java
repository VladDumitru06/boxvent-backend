package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.CreateFightCardRequest;
import com.boxvent.boxventwebsite.domain.CreateFightCardResponse;

public interface CreateFightCardUseCase {
    CreateFightCardResponse createFightCard(CreateFightCardRequest request);
}
