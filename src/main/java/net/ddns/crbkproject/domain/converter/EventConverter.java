package net.ddns.crbkproject.domain.converter;

import net.ddns.crbkproject.domain.exception.DomainException;
import net.ddns.crbkproject.domain.model.common.Event;
import net.ddns.crbkproject.infrastructure.mongo.model.MongoSoilMoisture;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static net.ddns.crbkproject.domain.exception.ExceptionCode.NOT_SUPPORTED_MEASURE;

@Component
public class EventConverter implements Converter<Event, MongoSoilMoisture> {

    @Override
    public MongoSoilMoisture convert(Event source) {
        try {
            return MongoSoilMoisture.of(
                    source.attributes().get("dev").toString(),
                    (int) source.attributes().get("sm"),
                    source.timestamp());
        } catch (Exception e) {
            throw new DomainException(NOT_SUPPORTED_MEASURE, source.type().name());
        }
    }
}
