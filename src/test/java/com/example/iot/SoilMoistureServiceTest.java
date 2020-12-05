package com.example.iot;

import com.example.iot.configuration.MqttConfiguration;
import com.example.iot.domain.event.SoilMoistureEvent;
import com.example.iot.domain.model.SoilMoisture;
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

class SoilMoistureServiceTest extends BaseTest {

    @Test
    @DisplayName("Converting casted event to domain object")
    void shouldConvertCastedEvent() throws JsonProcessingException {
        // given
        Message<?> message = prepareMessage();
        SoilMoistureEvent event = MqttConfiguration.castEvent(message);

        // when
        SoilMoisture soilMoisture = requireNonNull(conversionService.convert(event, SoilMoisture.class));

        // then
        assertThat(soilMoisture.getTimestamp())
                .isEqualTo(LocalDateTime.of(2020, 1, 1, 3, 0, 0));
    }

    private Message<?> prepareMessage() {
        String payload = "{\"sensor\":\"hw-080\",\"raw\":\"676\",\"pct\":\"52\"}";
        MutableMessageHeaders headers = new MutableMessageHeaders(Map.of(
                "mqtt_receivedRetained", false,
                "mqtt_id", 0,
                "mqtt_duplicate", false,
                "id", UUID.fromString("eeee0741-191b-5af9-76b8-487d948c6881"),
                "mqtt_receivedTopic", "soil-moisture-sensor",
                "mqtt_receivedQos", 0,
                "timestamp", 1577844000L));

        return new GenericMessage(payload, headers);
    }
}
