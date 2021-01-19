package net.ddns.crbkproject.domain.converter;

import net.ddns.crbkproject.domain.event.SoilMoistureEvent;
import net.ddns.crbkproject.domain.model.SoilMoisture;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class EventToSoilMoistureConverter implements Converter<SoilMoistureEvent, SoilMoisture> {

    @Override
    public SoilMoisture convert(SoilMoistureEvent source) {
        return new SoilMoisture(
                source.getSensor(),
                source.getPct(),
                source.getmVolt(),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getTimestamp()), ZoneOffset.UTC));
    }
}
