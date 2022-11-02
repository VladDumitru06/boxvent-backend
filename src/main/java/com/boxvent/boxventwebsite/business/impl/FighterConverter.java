package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.BoxingRecord;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.presistence.BoxingRecordRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
final class FighterConverter {
    BoxingRecordConverter boxingRecordConverter;
    public  Fighter convert(FighterEntity fighter) {
        return Fighter.builder()
                .id(fighter.getId())
                .name(fighter.getName())
                .boxingRecord(boxingRecordConverter.GetBoxingRecord(fighter))
                .build();
    }

}
