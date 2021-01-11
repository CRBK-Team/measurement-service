package com.example.iot.infrastructure;

import com.example.iot.domain.event.SoilMoistureEvent;
import com.example.iot.domain.event.TemperatureEvent;
import com.example.iot.domain.service.SoilMoistureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {
    private final SoilMoistureService soilMoistureService;
    private static final Logger log = LoggerFactory.getLogger(EventHandler.class);

    public EventHandler(SoilMoistureService soilMoistureService) {
        this.soilMoistureService = soilMoistureService;
    }

    @EventListener(SoilMoistureEvent.class)
    public void handleSoilMoistureMeasure(SoilMoistureEvent event) {
        soilMoistureService.addSoilMoisture(event);
        log.info("Received soil moisture measure: {}%", event.getPct());
    }

    @EventListener(TemperatureEvent.class)
    public void handleOtherMeasure(TemperatureEvent event) {
        log.info("Received temperature measure");
    }
}
