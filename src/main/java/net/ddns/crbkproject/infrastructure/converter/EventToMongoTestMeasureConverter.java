package net.ddns.crbkproject.infrastructure.converter;

import net.ddns.crbkproject.domain.model.common.Measurement;
import net.ddns.crbkproject.infrastructure.exception.DomainException;
import net.ddns.crbkproject.infrastructure.mongo.model.MongoTestMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static net.ddns.crbkproject.domain.model.common.ExceptionCode.NOT_SUPPORTED_MEASURE;

@Component
public class EventToMongoTestMeasureConverter implements Converter<Measurement, MongoTestMeasure> {

    @Override
    public MongoTestMeasure convert(Measurement source) {
        try {
            return MongoTestMeasure.of(
                    (int) source.attributes().get("H"),
                    (int) source.attributes().get("V"),
                    source.timestamp());
        } catch (Exception e) {
            throw new DomainException(NOT_SUPPORTED_MEASURE, source.type().name());
        }
    }
}
