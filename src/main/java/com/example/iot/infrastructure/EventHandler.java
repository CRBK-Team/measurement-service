package com.example.iot.infrastructure;

import com.example.iot.domain.SoilMoistureMeasuredEvent;
import com.example.iot.domain.TemperatureMeasuredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventHandler {

    @EventListener(SoilMoistureMeasuredEvent.class)
    public void handleSoilMoistureMeasure(SoilMoistureMeasuredEvent event) {
        log.info("Handled Soil Moisture measure: {}", event.toString());
    }

    @EventListener(TemperatureMeasuredEvent.class)
    public void handleOtherMeasure(TemperatureMeasuredEvent event) {
        log.info("Handled Temperature measure: {}", event.toString());
    }
}
