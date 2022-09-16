package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.presistence.entity.FighterEntity;

final class FighterConverter {
    private FighterConverter(){

    }

    public static Fighter convert(FighterEntity fighter) {
        return Fighter.builder()
                .id(fighter.getId())
                .name(fighter.getName())
                .record(fighter.getRecord())
                .build();
    }
}
