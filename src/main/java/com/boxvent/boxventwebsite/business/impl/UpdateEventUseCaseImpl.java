package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.UpdateEventSoldTicketsUseCase;
import com.boxvent.boxventwebsite.business.UpdateEventUseCase;
import com.boxvent.boxventwebsite.business.exception.AvailableTicketsLimitExceeded;
import com.boxvent.boxventwebsite.business.exception.InvalidCityException;
import com.boxvent.boxventwebsite.business.exception.InvalidEventException;
import com.boxvent.boxventwebsite.business.exception.SoldTicketsLimitExceeded;
import com.boxvent.boxventwebsite.domain.Event;
import com.boxvent.boxventwebsite.domain.UpdateEventRequest;
import com.boxvent.boxventwebsite.domain.UpdateEventSoldTicketsRequest;
import com.boxvent.boxventwebsite.presistence.CityRepository;
import com.boxvent.boxventwebsite.presistence.EventRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.CityEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;
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
public class UpdateEventUseCaseImpl implements UpdateEventUseCase {

    private final EventRepository eventRepository;
    private final CityRepository cityRepository;
    private final EventConverter eventConverter;
    private final CityConverter cityConverter;

    @Override
    @Transactional
    public Event UpdateEvent(UpdateEventRequest request) {
        EventEntity eventEntity = eventRepository.findById(request.getEventId())
                .orElseThrow(InvalidEventException::new);
        if (request.getEventName() != null && request.getEventName() != "") {
            eventEntity.setEventName(request.getEventName());
        }
        if (request.getCityName() != null && request.getCityName() != "") {
            CityEntity cityEntity = cityRepository.findByCityName(request.getCityName())
                .orElseThrow(InvalidCityException::new);
            eventEntity.setCity(cityEntity);
        }
        if (request.getAvailableTickets() != null ) {
            if(eventEntity.getSoldTickets() > request.getAvailableTickets())
                throw new SoldTicketsLimitExceeded();
            eventEntity.setAvailableTickets(request.getAvailableTickets());
        }
        if (request.getTicketPrice() != null) {
            eventEntity.setTicketPrice(request.getTicketPrice());
        }
        if (request.getDateTime() != null) {
            eventEntity.setDateTime(request.getDateTime());
        }
        if (request.getDescription() != null && request.getDescription() != "") {
            eventEntity.setDescription(request.getDescription());
        }
        if(request.getImage() != null && request.getImage() != "" && request.getImage().length() > 22){
            try {
                String base64EncodedImage = request.getImage().substring(22);
                byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedImage);
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                File outputDir = new File("src/main/java/com/boxvent/boxventwebsite/presistence/events");
                if (!outputDir.exists()) {
                    outputDir.mkdir();
                }
                File outputFile = new File(outputDir, eventEntity.getId() + ".jpg");
                ImageIO.write(image, "jpg", outputFile);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return eventConverter.convert(eventRepository.save(eventEntity));
    }

}
