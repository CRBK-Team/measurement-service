package com.example.iot.domain.converter;

import com.example.iot.domain.event.SoilMoistureEvent;
import com.example.iot.domain.model.SoilMoisture;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.time.LocalDateTime.ofEpochSecond;
import static java.time.OffsetDateTime.now;

@Component
public class EventToSoilMoistureConverter implements Converter<SoilMoistureEvent, SoilMoisture> {

    @Override
    public SoilMoisture convert(SoilMoistureEvent event) {
        return new SoilMoisture(
                event.getId(),
                event.getSensor(),
                event.getRaw(),
                (double) event.getPct() / 100,
                ofEpochSecond(event.getTimestamp(), 0, now().getOffset()));
    }
}
