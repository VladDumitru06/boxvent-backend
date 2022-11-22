package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.exception.FighterNameAlreadyExistsException;
import com.boxvent.boxventwebsite.domain.BoxingRecord;
import com.boxvent.boxventwebsite.domain.CreateFighterRequest;
import com.boxvent.boxventwebsite.domain.CreateFighterResponse;
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
class CreateFighterUseCaseImplTest {

    @Mock
    private FighterRepository fighterRepositoryMock;
    @Mock
    private BoxingRecordRepository boxingRecordRepositoryMock;
    @InjectMocks
    CreateFighterUseCaseImpl createFighterUseCase;

    @Test
    void createFighter_shouldReturnCreatedFighterId() {
        CreateFighterRequest createFighterRequest = CreateFighterRequest.builder().name("Vlad the boxer").wins(10L).draws(2L).loses(1L).build();
        FighterEntity fighterEntity = FighterEntity.builder().name(createFighterRequest.getName()).build();
        BoxingRecordEntity boxingRecordEntity = BoxingRecordEntity.builder().wins(10L).draws(2L).loses(1L).fighter(fighterEntity).build();

        when(fighterRepositoryMock.existsByName(createFighterRequest.getName()))
                .thenReturn(false);
        when(fighterRepositoryMock.save(fighterEntity))
                .thenReturn(FighterEntity.builder().id(1L).name(createFighterRequest.getName()).build());
        when(boxingRecordRepositoryMock.save(boxingRecordEntity))
                .thenReturn(boxingRecordEntity.builder().id(1L).wins(10L).draws(2L).loses(1L).fighter(fighterEntity).build());

        CreateFighterResponse actualResponse = createFighterUseCase.createFighter(createFighterRequest);

        CreateFighterResponse expectedResponse = CreateFighterResponse.builder().fighterId(1L).build();

        assertEquals(expectedResponse,actualResponse);
        verify(fighterRepositoryMock).existsByName(createFighterRequest.getName());
        verify(fighterRepositoryMock).save(fighterEntity);
        verify(boxingRecordRepositoryMock).save(boxingRecordEntity);
    }
    @Test
    void createFighter_shouldThrowFighterNameAlreadyExistsException()
    {
        CreateFighterRequest createFighterRequest = CreateFighterRequest.builder().name("Vlad the boxer").wins(10L).draws(2L).loses(1L).build();

        when(fighterRepositoryMock.existsByName(createFighterRequest.getName()))
                .thenReturn(true);

        assertThrows(FighterNameAlreadyExistsException.class,() -> createFighterUseCase.createFighter(createFighterRequest));
        verify(fighterRepositoryMock).existsByName(createFighterRequest.getName());
    }
}