package com.example.iot.api.converter;

import com.example.SoilMoistureView;
import com.example.iot.domain.model.SoilMoisture;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SoilMoistureToViewConverter implements Converter<SoilMoisture, SoilMoistureView> {

    @Override
    public SoilMoistureView convert(SoilMoisture source) {
        return new SoilMoistureView(
                source.getSensor(),
                source.getPercent(),
                source.getMillivolt(),
                source.getTimestamp().toString());
    }
}
