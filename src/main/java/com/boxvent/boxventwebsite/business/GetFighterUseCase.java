package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.domain.GetAllFightersResponse;

public interface GetFighterUseCase {
    Fighter getFighter(long id);
}
