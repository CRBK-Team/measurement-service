package com.example.iot.infrastructure;

import com.example.iot.domain.event.SoilMoistureMeasuredEvent;
import com.example.iot.domain.event.TemperatureMeasuredEvent;
import com.example.iot.domain.service.MeasureService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class EventHandler {
    MeasureService measureService;

    @EventListener(SoilMoistureMeasuredEvent.class)
    public void handleSoilMoistureMeasure(SoilMoistureMeasuredEvent event) {
        measureService.addSoilMoisture(event);
        log.info("Received soil moisture measure: {}%", event.getPct());
    }

    @EventListener(TemperatureMeasuredEvent.class)
    public void handleOtherMeasure(TemperatureMeasuredEvent event) {
        log.info("Received temperature measure");
    }
}
