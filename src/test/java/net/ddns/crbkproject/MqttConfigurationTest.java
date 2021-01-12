package net.ddns.crbkproject;

import net.ddns.crbkproject.configuration.MqttConfiguration;
import net.ddns.crbkproject.domain.event.SoilMoistureEvent;
import net.ddns.crbkproject.domain.model.SoilMoisture;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.integration.support.MutableMessageHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Soil moisture tests")
class MqttConfigurationTest extends BaseTestContainers {

    @Test
    @DisplayName("Converting casted event to domain object")
    void shouldConvertCastedEvent() throws JsonProcessingException {
        // given
        Message<?> message = prepareMessage();

        // when
        SoilMoistureEvent event = MqttConfiguration.castEvent(message);
        SoilMoisture soilMoisture = requireNonNull(conversionService.convert(event, SoilMoisture.class));

        // then
        assertThat(soilMoisture.getPercent()).isEqualTo(52);
        assertThat(soilMoisture.getMeasuredAt()).isEqualTo(LocalDateTime.of(2020, 1, 1, 3, 0, 0));
    }

    private Message<?> prepareMessage() {
        String payload = "{\"sensor\":\"hw-080\",\"pct\":\"52\",\"mVolt\":\"320\"}";
        MutableMessageHeaders headers = new MutableMessageHeaders(Map.of(
                "mqtt_receivedRetained", false,
                "mqtt_id", 0,
                "mqtt_duplicate", false,
                "id", UUID.fromString("eeee0741-191b-5af9-76b8-487d948c6881"),
                "mqtt_receivedTopic", "soil-moisture",
                "mqtt_receivedQos", 0,
                "timestamp", 1577844000L));

        return new GenericMessage<>(payload, headers);
    }
}
