package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.BoxingRecord;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.presistence.BoxingRecordRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.BoxingRecordEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;

public class BoxingRecordConverter {
    BoxingRecordRepository boxingRecordRepository;

    public BoxingRecord convert(BoxingRecordEntity boxingRecord) {
        return BoxingRecord.builder()
                .wins(boxingRecord.getWins())
                .draws(boxingRecord.getDraws())
                .loses(boxingRecord.getLoses())
                .build();
    }
    public BoxingRecord GetBoxingRecord(FighterEntity fighter) {
        return convert(boxingRecordRepository.findByFighter(fighter));
    }
}
