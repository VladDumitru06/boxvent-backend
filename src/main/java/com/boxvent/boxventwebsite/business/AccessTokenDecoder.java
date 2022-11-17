package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
