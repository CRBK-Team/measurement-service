package net.ddns.crbkproject.domain.converter;

import net.ddns.crbkproject.domain.event.SoilMoistureEvent;
import net.ddns.crbkproject.domain.model.SoilMoisture;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
public class EventToSoilMoistureConverter implements Converter<SoilMoistureEvent, SoilMoisture> {

    @Override
    public SoilMoisture convert(SoilMoistureEvent source) {
        return new SoilMoisture(
                source.getDevice(),
                source.getSm(),
                0,
                source.getTimestamp());
    }
}
