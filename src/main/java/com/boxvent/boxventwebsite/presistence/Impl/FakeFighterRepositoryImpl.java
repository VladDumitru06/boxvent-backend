package com.boxvent.boxventwebsite.presistence.Impl;

import com.boxvent.boxventwebsite.presistence.FighterRepository;
import com.boxvent.boxventwebsite.presistence.entity.FighterEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeFighterRepositoryImpl implements FighterRepository {
    private static long NEXT_ID = 1;
    private final List<FighterEntity> savedFighters;
    public FakeFighterRepositoryImpl() { this.savedFighters = new ArrayList<>();}

    @Override
    public boolean existByName(String name) {
        return this.savedFighters.stream()
                .anyMatch(fighterEntity -> fighterEntity.getName().equals(name));
    }

    @Override
    public FighterEntity add(FighterEntity fighter) {
        if(fighter.getId() == null) {
            fighter.setId(NEXT_ID);
            NEXT_ID++;
            this.savedFighters.add(fighter);
        }
        return fighter;
    }

    @Override
    public void deleteById(Long fighterId) {

    }

    @Override
    public List<FighterEntity> getAll() {
        return Collections.unmodifiableList(this.savedFighters);
    }

    @Override
    public FighterEntity findById(long fighterId) {
        return this.savedFighters
                .stream()
                .filter(fighterEntity -> fighterEntity.getId() == fighterId)
                .findFirst()
                .orElse(null);
    }
}
