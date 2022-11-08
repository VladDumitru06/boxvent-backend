package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.BoxingRecord;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.presistence.BoxingRecordRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.BoxingRecordEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class BoxingRecordConverter {
    private final BoxingRecordRepository boxingRecordRepository;

    public BoxingRecord convert(FighterEntity fighter) {
        BoxingRecordEntity boxingRecord = boxingRecordRepository.findByFighter(fighter);
        return BoxingRecord.builder()
                .wins(boxingRecord.getWins())
                .draws(boxingRecord.getDraws())
                .loses(boxingRecord.getLoses())
                .build();
    }
}
