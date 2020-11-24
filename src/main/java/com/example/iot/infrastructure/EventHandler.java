package com.example.iot.infrastructure;

import com.example.iot.domain.SoilMoistureMeasuredEvent;
import com.example.iot.domain.TemperatureMeasuredEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MethodInvoker;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.example.iot.domain.Event.EventType;
import static java.util.Optional.ofNullable;

@Component
@Slf4j
public class EventHandler {
    private static final String TOPIC = "mqtt_receivedTopic";


    public void handleMessage(SoilMoistureMeasuredEvent event) {
        log.info("Handled Soil Moisture measure: {}", event.toString());
    }


    public void handleMessage(TemperatureMeasuredEvent event) {
        //TODO: Temperature Sensor event class
        log.info("Handled Temperature measure");
    }


    private void invokeListenerMethod(Object event) {
        try {
            MethodInvoker methodInvoker = new MethodInvoker();
            methodInvoker.setTargetObject(this);
            methodInvoker.setTargetMethod("handleMessage");
            methodInvoker.setArguments(event);
            methodInvoker.prepare();
            methodInvoker.invoke();
        } catch (Exception e) {
            log.error("exception during invoke DocumentMessageListener method, invalid type of message or changed method name", e);
        }
    }

    public void process(Message<?> message) throws JsonProcessingException {
           Class<?> clazz = EventType.of(TOPIC).getClazz();
        Object o = new ObjectMapper().readValue(message.getPayload().toString(), clazz);

        invokeListenerMethod(o);
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
