package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.UpdateEventUseCase;
import com.boxvent.boxventwebsite.business.UpdateFighterUseCase;
import com.boxvent.boxventwebsite.business.exception.*;
import com.boxvent.boxventwebsite.domain.Event;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.domain.UpdateEventRequest;
import com.boxvent.boxventwebsite.domain.UpdateFighterRequest;
import com.boxvent.boxventwebsite.presistence.BoxingRecordRepository;
import com.boxvent.boxventwebsite.presistence.CityRepository;
import com.boxvent.boxventwebsite.presistence.EventRepository;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.BoxingRecordEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.CityEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Service
@AllArgsConstructor
public class UpdateFighterUseCaseImpl implements UpdateFighterUseCase {

    private final FighterRepository fighterRepository;
    private final BoxingRecordRepository boxingRecordRepository;
    private final FighterConverter fighterConverter;

    @Override
    @Transactional
    public Fighter UpdateFighter(UpdateFighterRequest request) {
        FighterEntity fighterEntity = fighterRepository.findById(request.getFighterId())
                .orElseThrow(InvalidFighterException::new);
        BoxingRecordEntity boxingRecordEntity = boxingRecordRepository.findByFighter(fighterEntity);
        if (request.getFighterName() != null && request.getFighterName() != "") {
            fighterEntity.setName(request.getFighterName());
        }
        if(boxingRecordEntity != null)
        {
            if(request.getWins() != null)
                boxingRecordEntity.setWins(request.getWins());
            if(request.getDraws() != null)
                boxingRecordEntity.setDraws(request.getDraws());
            if(request.getLoses() != null)
                boxingRecordEntity.setLoses(request.getLoses());
        }
        else
        {
            throw new InvalidBoxingRecordException();
        }
        if(request.getImage() != null && request.getImage() != "" && request.getImage().length() > 22){
            try {
                String base64EncodedImage = request.getImage().substring(22);
                byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedImage);
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                File outputDir = new File("src/main/java/com/boxvent/boxventwebsite/presistence/fighters");
                if (!outputDir.exists()) {
                    outputDir.mkdir();
                }
                File outputFile = new File(outputDir, request.getFighterId() + ".jpg");
                ImageIO.write(image, "jpg", outputFile);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        boxingRecordRepository.save(boxingRecordEntity);
        return fighterConverter.convert(fighterRepository.save(fighterEntity));
    }

}
