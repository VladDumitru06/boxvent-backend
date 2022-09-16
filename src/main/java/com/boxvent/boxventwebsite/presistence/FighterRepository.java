package com.boxvent.boxventwebsite.presistence;

import com.boxvent.boxventwebsite.presistence.entity.FighterEntity;

import java.util.List;
import java.util.Optional;

public interface FighterRepository {
    boolean existByName(String name);
    FighterEntity add(FighterEntity fighter);
    void deleteById(Long fighterId);
    List<FighterEntity> getAll();
    FighterEntity  findById(long fighterId);
}
