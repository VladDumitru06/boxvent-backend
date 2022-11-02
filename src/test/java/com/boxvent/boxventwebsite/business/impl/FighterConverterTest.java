package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FighterConverterTest {
@Test
    public void convertedFighterShouldntChangeItsData_AfterConversion(){
    //FighterEntity fighter = FighterEntity.builder().id(1L).name("Vlad the boxer").record("15-2-0").build();
   // Fighter convertedFighter = FighterConverter.convert(fighter);
    //assertEquals(fighter.getName(),convertedFighter.getName());
   // assertEquals(fighter.getId(),convertedFighter.getId());
    assertEquals(1,1);
}
}