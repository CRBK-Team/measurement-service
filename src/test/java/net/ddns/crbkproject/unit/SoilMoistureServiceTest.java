package net.ddns.crbkproject.unit;

import net.ddns.crbkproject.domain.exception.DomainException;
import net.ddns.crbkproject.domain.model.common.Event;
import net.ddns.crbkproject.domain.model.common.EventType;
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
        Event event = Event.of(EventType.SOIL_MOISTURE);
        event.assignAttribute(Map.entry("dev", "test"));
        event.assignAttribute(Map.entry("sm", 78));
        soilMoistureService.add(event);

        // when
        List<SoilMoisture> mongoSoilMoistureList = soilMoistureService.findAllPageable(PageRequest.of(0, 10));

        // then
        assertThat(mongoSoilMoistureList).hasSize(1);
    }

    @Test
    void shouldThrowWhenFindAllPageable() {
        // given
        Event event = Event.of(EventType.SOIL_MOISTURE);
        event.assignAttribute(Map.entry("dev", "test"));
        event.assignAttribute(Map.entry("sm", 152));

        // when
        DomainException exception = (DomainException) catchThrowableOfType(
                () -> soilMoistureService.add(event), ConversionFailedException.class).getCause();

        // then
        assertThat(exception.code().httpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
