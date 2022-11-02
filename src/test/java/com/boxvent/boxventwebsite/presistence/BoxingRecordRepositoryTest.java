package com.boxvent.boxventwebsite.presistence;

import com.boxvent.boxventwebsite.presistence.Impl.entity.BoxingRecordEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoxingRecordRepositoryTest {

    @Autowired
    BoxingRecordRepository boxingRecordRepository;
    @Autowired
    FighterRepository fighterRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    void save_ShouldSaveBoxingRecordWithAllFields()
    {
        FighterEntity fighter = FighterEntity.builder().name("Jackie Chan Jr.").build();

        if(entityManager.contains(fighter))
        {
            fighter = fighterRepository.findByName(fighter.getName());
        }
        else
        {
            entityManager.persist(fighter);
        }
        BoxingRecordEntity boxingRecord = BoxingRecordEntity.builder()
                .wins(10L)
                .draws(2L)
                .loses(1L)
                .fighter(fighter)
                .build();
        BoxingRecordEntity savedRecord = boxingRecordRepository.save(boxingRecord);

        BoxingRecordEntity expectedRecord = BoxingRecordEntity.builder()
                .id(savedRecord.getId())
                .fighter(fighter)
                .wins(10L)
                .draws(2L)
                .loses(1L)
                .build();

        assertEquals(expectedRecord,savedRecord);

    }
    @Test
    void findByFighter_ShouldReturnFightersRecord()
    {
        FighterEntity fighter = FighterEntity.builder().name("Jackie Chan Jr.").build();

        if(entityManager.contains(fighter))
        {
            fighter = fighterRepository.findByName(fighter.getName());
        }
        else
        {
            entityManager.persist(fighter);
        }
        BoxingRecordEntity boxingRecord = BoxingRecordEntity.builder()
                .wins(10L)
                .draws(2L)
                .loses(1L)
                .fighter(fighter)
                .build();
        boxingRecordRepository.save(boxingRecord);
        BoxingRecordEntity returnedRecord = boxingRecordRepository.findByFighter(fighter);
        BoxingRecordEntity expectedRecord = BoxingRecordEntity.builder()
                .id(returnedRecord.getId())
                .wins(10L)
                .draws(2L)
                .loses(1L)
                .fighter(fighter)
                .build();
        assertEquals(expectedRecord,returnedRecord);

    }
    @Test
    void findByFighter_ShouldReturnNullWhenNoFightersRecordFound()
    {
        FighterEntity fighter = FighterEntity.builder().name("Jackie Chan Jr.").build();

        if(entityManager.contains(fighter))
        {
            fighter = fighterRepository.findByName(fighter.getName());
        }
        else
        {
            entityManager.persist(fighter);
        }
        BoxingRecordEntity returnedRecord = boxingRecordRepository.findByFighter(fighter);
        assertNull(returnedRecord);

    }
}