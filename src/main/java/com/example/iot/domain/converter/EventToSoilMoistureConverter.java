package com.example.iot.domain.converter;

import com.example.iot.domain.event.SoilMoistureEvent;
import com.example.iot.domain.model.SoilMoisture;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Component
public class EventToSoilMoistureConverter implements Converter<SoilMoistureEvent, SoilMoisture> {

    @Override
    public SoilMoisture convert(SoilMoistureEvent source) {
        return new SoilMoisture(
                source.getSensor(),
                source.getPct(),
                source.getmVolt(),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getTimestamp()), ZoneId.systemDefault()));
    }
}
