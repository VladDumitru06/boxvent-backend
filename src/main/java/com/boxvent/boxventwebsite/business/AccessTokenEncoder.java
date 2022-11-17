package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
