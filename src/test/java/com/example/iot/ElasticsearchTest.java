package com.example.iot;

import com.example.SoilMoistureView;
import com.example.iot.configuration.MqttConfiguration;
import com.example.iot.domain.event.SoilMoistureEvent;
import com.example.iot.domain.model.SoilMoisture;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.integration.support.MutableMessageHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ElasticsearchTest extends BaseTestContainers {

    @Test
    void shouldFindAll() {
        // given
        addEvent();
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<SoilMoistureView> soilMoistures = soilMoistureApi.findAllPageable(pageable);

        // then
        assertThat(soilMoistures).hasSize(1);
    }

    @Test
    void test2() {
        //assertThat(ELASTICSEARCH_CONTAINER.isRunning()).isTrue();
    }

    private void addEvent() {
        Message<?> message = prepareMessage();
        SoilMoistureEvent event = null;
        try {
            event = MqttConfiguration.castEvent(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        measureService.addSoilMoisture(event);
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

        return new GenericMessage<>(payload, headers);
    }
}
