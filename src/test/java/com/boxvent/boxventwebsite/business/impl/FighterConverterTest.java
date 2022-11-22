package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.BoxingRecord;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.presistence.BoxingRecordRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.BoxingRecordEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    BoxingRecordEntity fighterRecord = BoxingRecordEntity.builder().id(1L).fighter(fighter).wins(1L).draws(0L).loses(0L).build();

    when(boxingRecordRepositoryMock.findByFighter(fighter))
            .thenReturn(fighterRecord);

    Fighter convertedFighter = fighterConverter.convert(fighter);

    assertEquals(fighter.getName(),convertedFighter.getName());
    assertEquals(fighter.getId(),convertedFighter.getId());
    assertEquals(BoxingRecord.builder()
                    .wins(fighterRecord.getWins())
                    .draws(fighterRecord.getDraws())
                    .loses(fighterRecord.getLoses())
                    .build(), convertedFighter.getBoxingRecord());
    verify(boxingRecordRepositoryMock).findByFighter(fighter);
}
}