package net.ddns.crbkproject.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.ddns.crbkproject.domain.model.common.Measurement;
import net.ddns.crbkproject.domain.model.common.MeasurementType;
import net.ddns.crbkproject.domain.model.measurement.SoilMoisture;
import net.ddns.crbkproject.infrastructure.mongo.model.MongoSoilMoisture;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class SoilMoistureServiceIntegrationTest extends BaseIntegrationTest {

    @Test
    void shouldConvertCastedEvent() throws JsonProcessingException {
        // given
        Message<?> message = new GenericMessage<>("{\"dev\":\"hw-080\",\"sm\":52,\"temp\":22}");

        // when
        Set<Measurement> measurements = measurementHandler.castMeasurement(message.getPayload().toString());
        SoilMoisture soilMoisture = measurements.stream()
                .filter(m -> m.type().equals(MeasurementType.SOIL_MOISTURE))
                .map(measurement -> conversionService.convert(conversionService.convert(measurement, MongoSoilMoisture.class), SoilMoisture.class))
                .findAny().get();

        // then
        assertThat(soilMoisture.percent().value()).isEqualTo(52);
    }
}
