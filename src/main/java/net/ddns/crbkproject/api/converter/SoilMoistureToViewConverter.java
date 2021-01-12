package net.ddns.crbkproject.api.converter;

import net.ddns.crbkproject.SoilMoistureView;
import net.ddns.crbkproject.domain.model.SoilMoisture;
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
                source.getMeasuredAt().toString());
    }
}
