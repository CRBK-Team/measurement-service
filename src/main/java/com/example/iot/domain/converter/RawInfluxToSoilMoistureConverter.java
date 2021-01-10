package com.example.iot.domain.converter;

import com.example.iot.domain.model.SoilMoisture;
import com.example.iot.domain.model.SoilMoistureRawInflux;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Component
public class RawInfluxToSoilMoistureConverter implements Converter<SoilMoistureRawInflux, SoilMoisture> {

    @Override
    public SoilMoisture convert(SoilMoistureRawInflux source) {
        return new SoilMoisture(
                source.getSensor(),
                source.getPercent().intValue(),
                Optional.ofNullable(source.getVoltage()).map(Integer::parseInt).orElse(0),
                LocalDateTime.ofInstant(source.getTimestamp(), ZoneId.systemDefault()));
    }
}
