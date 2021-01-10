package com.example.iot.domain.converter;

import com.example.iot.domain.event.SoilMoistureEvent;
import com.example.iot.domain.model.SoilMoistureRawInflux;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class EventToSoilMoistureConverter implements Converter<SoilMoistureEvent, SoilMoistureRawInflux> {

    @Override
    public SoilMoistureRawInflux convert(SoilMoistureEvent event) {
        return new SoilMoistureRawInflux(
                event.getSensor(),
                (long) event.getPct(),
                String.valueOf(event.getmVolt()),
                Instant.ofEpochSecond(event.getTimestamp(), 0));
    }
}
