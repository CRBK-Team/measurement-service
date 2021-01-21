package net.ddns.crbkproject.domain.converter;

import net.ddns.crbkproject.domain.event.WeatherEvent;
import net.ddns.crbkproject.domain.model.Weather;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class EventToWeatherConverter implements Converter<WeatherEvent, Weather> {

    @Override
    public Weather convert(WeatherEvent source) {
        return new Weather(
                source.getSensor(),
                source.getTemp(),
                source.getHum(),
                source.getPre(),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getTimestamp()), ZoneOffset.UTC));
    }
}
