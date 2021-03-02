package net.ddns.crbkproject.infrastructure.converter;

import net.ddns.crbkproject.infrastructure.exception.DomainException;
import net.ddns.crbkproject.domain.model.common.Measurement;
import net.ddns.crbkproject.infrastructure.mongo.model.MongoSoilMoisture;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static net.ddns.crbkproject.domain.model.common.ExceptionCode.NOT_SUPPORTED_MEASURE;

@Component
public class EventToMongoSoilMoistureConverter implements Converter<Measurement, MongoSoilMoisture> {

    @Override
    public MongoSoilMoisture convert(Measurement source) {
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
