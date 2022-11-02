package com.boxvent.boxventwebsite.presistence;

import com.boxvent.boxventwebsite.presistence.Impl.entity.BoxingRecordEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoxingRecordRepository extends JpaRepository<BoxingRecordEntity,Long> {
    BoxingRecordEntity findByFighter(FighterEntity fighter);
}
