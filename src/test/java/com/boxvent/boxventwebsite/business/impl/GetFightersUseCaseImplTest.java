package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.BoxingRecord;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.domain.GetAllFightersResponse;
import com.boxvent.boxventwebsite.presistence.BoxingRecordRepository;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.BoxingRecordEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetFightersUseCaseImplTest {
    @Mock
    private FighterRepository fighterRepositoryMock;
    @Mock
    private BoxingRecordRepository boxingRecordRepositoryMock;
    @InjectMocks
    BoxingRecordConverter boxingRecordConverter;
  //  @InjectMocks
  //  GetFightersUseCaseImpl getFightersUseCase;
    //@InjectMocks
   // FighterConverter fighterConverter;


    @Test
    void getFighters_shouldReturnAllFightersConverted() {

       // BoxingRecordRepository boxingRecordRepositoryMock = mock(BoxingRecordRepository.class);
       // FighterRepository fighterRepositoryMock = mock(FighterRepository.class);

       // BoxingRecordConverter boxingRecordConverter = new BoxingRecordConverter(boxingRecordRepositoryMock);
        FighterConverter fighterConverter = new FighterConverter(boxingRecordConverter);

        FighterEntity vladEntity = FighterEntity.builder().id(1L).name("Vlad the boxer").build();
        FighterEntity dejiEntity = FighterEntity.builder().id(1L).name("Deji").build();

        BoxingRecordEntity vladRecordEntity = BoxingRecordEntity.builder().fighter(vladEntity).id(1L).wins(10L).draws(0L).loses(2L).build();
        BoxingRecordEntity dejiRecordEntity = BoxingRecordEntity.builder().fighter(dejiEntity).id(1L).wins(1L).draws(0L).loses(0L).build();

        when(boxingRecordRepositoryMock.findByFighter(vladEntity))
                .thenReturn(vladRecordEntity);
        when(boxingRecordRepositoryMock.findByFighter(dejiEntity))
                .thenReturn(dejiRecordEntity);
        when(fighterRepositoryMock.findAll())
                .thenReturn(List.of(vladEntity,dejiEntity));

        GetFightersUseCaseImpl getFightersUseCase = new GetFightersUseCaseImpl(fighterRepositoryMock, fighterConverter);
        GetAllFightersResponse actualResponse = getFightersUseCase.getFighters();

        BoxingRecord vladRecord = BoxingRecord.builder().wins(10L).draws(0L).loses(2L).build();
        BoxingRecord dejiRecord = BoxingRecord.builder().wins(1L).draws(0L).loses(0L).build();

        Fighter vlad = Fighter.builder().id(1L).name("Vlad the boxer").boxingRecord(vladRecord).build();
        Fighter deji = Fighter.builder().id(1L).name("Deji").boxingRecord(dejiRecord).build();

        GetAllFightersResponse expectedResponse = GetAllFightersResponse.builder().fighters(List.of(vlad,deji)).build();

        assertEquals(expectedResponse,actualResponse);
        verify(fighterRepositoryMock).findAll();
        verify(boxingRecordRepositoryMock).findByFighter(vladEntity);
        verify(boxingRecordRepositoryMock).findByFighter(dejiEntity);
    }
}