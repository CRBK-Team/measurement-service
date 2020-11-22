package com.example.iot.infrastructure;

import com.example.iot.domain.GenericEvent;
import com.example.iot.domain.SoilMoistureMeasuredEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.example.iot.domain.EventType.SOIL_MOISTURE_SENSOR;
import static com.example.iot.domain.EventType.TEMPERATURE_SENSOR;

@Component
@Slf4j
public class EventListeners {

    @EventListener(condition = "@eventListeners.handleSoilMoistureEvent(event)")
    public void handleSoilMoistureMeasure(GenericEvent event) throws JsonProcessingException {
        SoilMoistureMeasuredEvent measuredEvent = new ObjectMapper().readValue(event.getObject(), SoilMoistureMeasuredEvent.class);
        log.info("Handled Soil Moisture measure: {}", measuredEvent.toString());
    }

    @EventListener(condition = "@eventListeners.handleTemperatureEvent(event)")
    public void handleOtherMeasure(GenericEvent event) {
        //TODO: Temperature Sensor event class
        log.info("Handled Temperature measure");
    }

    public boolean handleSoilMoistureEvent(PayloadApplicationEvent<GenericEvent> event) {
        return event.getPayload().getType().equalsIgnoreCase(SOIL_MOISTURE_SENSOR.getName());
    }

    public boolean handleTemperatureEvent(PayloadApplicationEvent<GenericEvent> event) {
        return event.getPayload().getType().equalsIgnoreCase(TEMPERATURE_SENSOR.getName());
    }
}
