package com.example.iot.infrastructure;

import com.example.iot.domain.SoilMoistureMeasuredEvent;
import com.example.iot.domain.TemperatureMeasuredEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.example.iot.domain.Event.EVENT_CLASSES;
import static com.example.iot.domain.Event.EventType;
import static java.util.Optional.ofNullable;

@Component
@Slf4j
public class EventHandler {
    private static final String TOPIC = "mqtt_receivedTopic";

    @EventListener(SoilMoistureMeasuredEvent.class)
    public void handleSoilMoistureMeasure(SoilMoistureMeasuredEvent event) {
        log.info("Handled Soil Moisture measure: {}", event.toString());
    }

    @EventListener(TemperatureMeasuredEvent.class)
    public void handleOtherMeasure(TemperatureMeasuredEvent event) {
        //TODO: Temperature Sensor event class
        log.info("Handled Temperature measure");
    }

    public <T> T castEvent(Message<?> message) throws JsonProcessingException {
        String topic = ofNullable(message.getHeaders().get(TOPIC))
                .map(Object::toString)
                .orElseThrow(RuntimeException::new);

        Class<?> clazz = EVENT_CLASSES.get(EventType.of(topic));

        @SuppressWarnings("unchecked")
        T event = (T) clazz.cast(new ObjectMapper().readValue(message.getPayload().toString(), clazz));

        return event;
    }

    public void logEvent(MessageHeaders headers) {
        log.info("{} received message: { id: {}, topic: {} }", getTime(headers),
                getHeaderValue(headers, "id"), getHeaderValue(headers, TOPIC));
    }

    private String getHeaderValue(MessageHeaders headers, String headerKey) {
        return ofNullable(headers.get(headerKey))
                .map(Object::toString)
                .orElseThrow(RuntimeException::new);
    }

    private String getTime(MessageHeaders headers) {
        long epoch = ofNullable(headers.get("timestamp"))
                .map(timestamp -> Long.parseLong(timestamp.toString()))
                .orElseThrow(RuntimeException::new);

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epoch), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
