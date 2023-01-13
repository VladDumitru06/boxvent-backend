package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.domain.User;

public interface GetUserUseCase {
    User getUser(long id);
}
