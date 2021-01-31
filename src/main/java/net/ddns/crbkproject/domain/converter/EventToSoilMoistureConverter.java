package net.ddns.crbkproject.domain.converter;

import net.ddns.crbkproject.domain.exception.DomainException;
import net.ddns.crbkproject.domain.model.Event;
import net.ddns.crbkproject.domain.model.SoilMoisture;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static net.ddns.crbkproject.domain.exception.ExceptionCode.NOT_SUPPORTED_MEASURE;

@Component
public class EventToSoilMoistureConverter implements Converter<Event, SoilMoisture> {

    @Override
    public SoilMoisture convert(Event source) {
        try {
            return new SoilMoisture(
                    source.getAttributes().get("dev").toString(),
                    (int) source.getAttributes().get("sm"),
                    source.getTimestamp());
        } catch (Exception e) {
            throw new DomainException(NOT_SUPPORTED_MEASURE, source.getType().name());
        }
    }
}
