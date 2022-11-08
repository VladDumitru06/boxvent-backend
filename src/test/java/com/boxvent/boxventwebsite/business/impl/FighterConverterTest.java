package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.presistence.BoxingRecordRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class FighterConverterTest {

    @Mock
    private BoxingRecordRepository boxingRecordRepositoryMock;
    @InjectMocks
    BoxingRecordConverter boxingRecordConverter;
@Test
    public void convert_ShouldReturnSameData(){

    FighterConverter fighterConverter = new FighterConverter(boxingRecordConverter);
    FighterEntity fighter = FighterEntity.builder().id(1L).name("Vlad the boxer").build();
    Fighter convertedFighter = fighterConverter.convert(fighter);
    assertEquals(fighter.getName(),convertedFighter.getName());
    assertEquals(fighter.getId(),convertedFighter.getId());
    //assertEquals(convertedFighter,fighter);

}
}