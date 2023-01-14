package com.boxvent.boxventwebsite.presistence;

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
class FighterRepositoryTest {

    @Autowired
    FighterRepository fighterRepository;
    @Autowired
    EntityManager entityManager;
    @Test
    void save_ShouldSaveFighterWithAllFields()
    {
        FighterEntity savedFighter = saveFighter("Jackie Chan Jr.");

        FighterEntity expectedFighter = FighterEntity.builder().id(savedFighter.getId()).name("Jackie Chan Jr.").profile("profile").build();

        assertEquals(savedFighter,expectedFighter);

    }
    @Test
    void existsByName_ShouldReturnTrueWhenFighterFound() {

        FighterEntity fighter = saveFighter("Jackie Chan Jr.");
        boolean answer = fighterRepository.existsByName("Jackie Chan Jr.");
        assertTrue(answer);
    }
    @Test
    void existsByName_ShouldReturnFalseWhenNoFighterFound() {

        boolean answer = fighterRepository.existsByName("Jackie Chan Jr.");
        assertFalse(answer);
    }
    @Test
    void findByName_ShouldReturnTheFoundFighter() {
        FighterEntity fighter = saveFighter("Jackie Chan Jr.");
        FighterEntity foundFighter = fighterRepository.findByName("Jackie Chan Jr.");
        FighterEntity expectedFighter = FighterEntity.builder().id(foundFighter.getId()).name("Jackie Chan Jr.").profile("profile").build();
        assertEquals(expectedFighter,foundFighter);
    }
    @Test
    void findByName_ShouldReturnNullWhenNoFighterFound() {

        FighterEntity missingFighter = fighterRepository.findByName("Jackie Chan Jr.");
        assertNull(missingFighter);
    }
    FighterEntity saveFighter(String name)
    {
        FighterEntity fighter = FighterEntity.builder().name(name).profile("profile").build();
        return fighterRepository.save(fighter);
    }
}