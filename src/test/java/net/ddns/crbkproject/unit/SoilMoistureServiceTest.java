package net.ddns.crbkproject.unit;

import net.ddns.crbkproject.infrastructure.exception.DomainException;
import net.ddns.crbkproject.domain.model.common.Measurement;
import net.ddns.crbkproject.domain.model.common.MeasurementType;
import net.ddns.crbkproject.domain.model.measurement.SoilMoisture;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

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
        List<SoilMoisture> mongoSoilMoistureList = soilMoistureService.findAllPageable(PageRequest.of(0, 10));

        // then
        assertThat(mongoSoilMoistureList).hasSize(1);
    }

    @Test
    void shouldThrowWhenFindAllPageable() {
        // given
        Measurement measurement = Measurement.of(MeasurementType.SOIL_MOISTURE);
        measurement.assignAttribute(Map.entry("dev", "test"));
        measurement.assignAttribute(Map.entry("sm", 152));

        // when
        DomainException exception = (DomainException) catchThrowableOfType(
                () -> soilMoistureService.add(measurement), ConversionFailedException.class).getCause();

        // then
        assertThat(exception.code().httpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
