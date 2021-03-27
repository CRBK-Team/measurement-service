package net.ddns.crbkproject.unit;

import net.ddns.crbkproject.domain.model.common.Measurement;
import net.ddns.crbkproject.domain.model.common.MeasurementType;
import net.ddns.crbkproject.domain.model.measurement.SoilMoisture;
import net.ddns.crbkproject.infrastructure.exception.DomainException;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

class SoilMoistureServiceTest extends BaseUnitTest {

    @Test
    void shouldFindAllPageable() {
        // given
        Measurement measurement = Measurement.of(MeasurementType.SOIL_MOISTURE);
        measurement.assignAttribute(Map.entry("dev", "test"));
        measurement.assignAttribute(Map.entry("sm", 78));
        soilMoistureService.add(measurement);

        // when
        List<SoilMoisture> soilMoisture = requireNonNull(soilMoistureService.findAllPageable(PageRequest.of(0, 10)).block()).getContent();

        // then
        assertThat(soilMoisture).hasSize(1);
    }

    @Test
    void shouldThrowWhenFindAllPageable() {
        // given
        Measurement measurement = Measurement.of(MeasurementType.SOIL_MOISTURE);
        measurement.assignAttribute(Map.entry("dev", "test"));
        measurement.assignAttribute(Map.entry("sm", 152));

        // when
        Mono<SoilMoisture> soilMoistureMono = soilMoistureService.add(measurement);
        DomainException exception = (DomainException) catchThrowableOfType(
                soilMoistureMono::block, ConversionFailedException.class).getCause();

        // then
        assertThat(exception.code().httpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
