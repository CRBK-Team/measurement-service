package net.ddns.crbkproject.domain.converter;

import net.ddns.crbkproject.infrastructure.exception.DomainException;
import net.ddns.crbkproject.domain.model.measurement.Device;
import net.ddns.crbkproject.domain.model.measurement.MeasuredAt;
import net.ddns.crbkproject.domain.model.measurement.Percent;
import net.ddns.crbkproject.domain.model.measurement.SoilMoisture;
import net.ddns.crbkproject.infrastructure.mongo.model.MongoSoilMoisture;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static net.ddns.crbkproject.domain.model.common.ExceptionCode.NOT_VALIDATED_MEASURE;

@Component
public class MongoToSoilMoistureConverter implements Converter<MongoSoilMoisture, SoilMoisture> {

    @Override
    public SoilMoisture convert(MongoSoilMoisture source) {
        try {
            return SoilMoisture.of(
                    source.id().toString(),
                    Device.of(source.device()),
                    Percent.of(source.percent()),
                    MeasuredAt.of(source.getMeasuredAt()));
        } catch (Exception e) {
            throw new DomainException(NOT_VALIDATED_MEASURE);
        }
    }
}
