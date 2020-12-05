package com.example.iot.infrastructure;

import com.example.iot.domain.event.SoilMoistureEvent;
import com.example.iot.domain.event.TemperatureEvent;
import com.example.iot.domain.service.MeasureService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@Slf4j
public class EventHandler {
    MeasureService measureService;

    @EventListener(SoilMoistureEvent.class)
    public void handleSoilMoistureMeasure(SoilMoistureEvent event) {
        measureService.addSoilMoisture(event);
        log.info("Received soil moisture measure: {}%", event.getPct());
    }

    @EventListener(TemperatureEvent.class)
    public void handleOtherMeasure(TemperatureEvent event) {
        log.info("Received temperature measure");
    }
}
