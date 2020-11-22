package com.example.iot.infrastructure;

import com.example.iot.domain.GenericEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventListeners {

    @EventListener(condition = "#event.type == T(com.example.iot.domain.EventType).SOIL_MOISTURE_SENSOR.name")
    public void handleSoilMoistureMeasure(GenericEvent event) {
        System.out.println("soil");
    }

    @EventListener(condition = "#event.type == T(com.example.iot.domain.EventType).TEMPERATURE_SENSOR.name")
    public void handleOtherMeasure(GenericEvent event) {
        System.out.println("temp");
    }
}
