package net.ddns.crbkproject.api.converter;

import net.ddns.crbkproject.WeatherView;
import net.ddns.crbkproject.domain.model.Weather;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WeatherToViewConverter implements Converter<Weather, WeatherView> {
    @Override
    public WeatherView convert(Weather source) {
        return new WeatherView(
                source.getSensor(),
                source.getTemp(),
                source.getHumidity(),
                source.getPressure(),
                source.getMeasuredAt().toString());
    }
}
