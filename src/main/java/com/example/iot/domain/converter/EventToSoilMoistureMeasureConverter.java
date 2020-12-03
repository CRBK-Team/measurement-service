package com.example.iot.domain.converter;

import com.example.iot.domain.event.SoilMoistureMeasuredEvent;
import com.example.iot.domain.model.SoilMoisture;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventToSoilMoistureMeasureConverter implements Converter<SoilMoistureMeasuredEvent, SoilMoisture> {

    @Override
    public SoilMoisture convert(SoilMoistureMeasuredEvent event) {
        return new SoilMoisture(event.getSensor(), event.getRaw(), (double) event.getPct() / 100);
    }
}
