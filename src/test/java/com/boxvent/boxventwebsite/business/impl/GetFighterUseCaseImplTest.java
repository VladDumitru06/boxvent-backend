package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.BoxingRecord;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.presistence.BoxingRecordRepository;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
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

class GetFighterUseCaseImplTest {

    @Mock
    private FighterRepository fighterRepositoryMock;
    @Mock
    private BoxingRecordRepository boxingRecordRepositoryMock;
    @InjectMocks
    BoxingRecordConverter boxingRecordConverter;

    @Test
    void getFighter() {
        BoxingRecord boxingRecord = BoxingRecord.builder().wins(10L).draws(0L).loses(2L).build();
        BoxingRecordEntity boxingRecordEntity = BoxingRecordEntity.builder().id(1L).wins(10L).draws(0L).loses(2L).build();
        Fighter fighter = Fighter.builder().id(1L).name("Vlad the boxer").boxingRecord(boxingRecord).build();
        FighterEntity fighterEntity = FighterEntity.builder().id(1L).name("Vlad the boxer").build();

        when(fighterRepositoryMock.getReferenceById(1L))
                .thenReturn(FighterEntity.builder().name(fighter.getName()).id(fighter.getId()).build());
        when(boxingRecordRepositoryMock.findByFighter(fighterEntity))
                .thenReturn(boxingRecordEntity);

        FighterConverter fighterConverter = new FighterConverter(boxingRecordConverter);
        GetFighterUseCaseImpl getFighterUseCase = new GetFighterUseCaseImpl(fighterRepositoryMock,fighterConverter);
        Fighter actualResult = getFighterUseCase.getFighter(1L);

        assertEquals(fighter,actualResult);
        verify(fighterRepositoryMock).getReferenceById(1L);
        verify(boxingRecordRepositoryMock).findByFighter(fighterEntity);

    }
}