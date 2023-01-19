package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.presistence.BoxingRecordRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.BoxingRecordEntity;
import lombok.AllArgsConstructor;
import com.boxvent.boxventwebsite.business.CreateFighterUseCase;
import com.boxvent.boxventwebsite.business.exception.FighterNameAlreadyExistsException;
import com.boxvent.boxventwebsite.domain.CreateFighterRequest;
import com.boxvent.boxventwebsite.domain.CreateFighterResponse;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
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
public class CreateFighterUseCaseImpl implements CreateFighterUseCase {
    private final FighterRepository fighterRepository;
    private final BoxingRecordRepository boxingRecordRepository;
    @Override
    @Transactional
    public CreateFighterResponse createFighter(CreateFighterRequest request) {
        if(fighterRepository.existsByName(request.getName())){
            throw new FighterNameAlreadyExistsException();
        }
        FighterEntity savedFighter = saveNewFighter(request);
        return CreateFighterResponse.builder().fighterId(savedFighter.getId()).build();
    }
    private FighterEntity saveNewFighter(CreateFighterRequest request) {
        try {
            FighterEntity newFighter = FighterEntity.builder()
                    .name(request.getName())
                    .profile("/" + request.getName() + "/profilePic")
                    .build();

            FighterEntity savedFighter = fighterRepository.save(newFighter);

            String base64EncodedImage = request.getImage().substring(22);
            byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedImage);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
            File outputDir = new File("src/main/java/com/boxvent/boxventwebsite/presistence/fighters");
            if (!outputDir.exists()) {
                outputDir.mkdir();
            }
            File outputFile = new File(outputDir, savedFighter.getId() + ".jpg");
            if(image != null) {
                ImageIO.write(image, "jpg", outputFile);
            }


        saveNewRecord(request.getWins(), request.getDraws(), request.getLoses(), newFighter);
        return  savedFighter;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private BoxingRecordEntity saveNewRecord(Long wins,Long draws,Long loses,FighterEntity fighter)
    {
        BoxingRecordEntity newRecord = BoxingRecordEntity.builder()
                .wins(wins)
                .draws(draws)
                .loses(loses)
                .fighter(fighter)
                .build();
        return boxingRecordRepository.save(newRecord);
    }
}
