package net.ddns.crbkproject.infrastructure;

import net.ddns.crbkproject.domain.event.SoilMoistureEvent;
import net.ddns.crbkproject.domain.event.TemperatureEvent;
import net.ddns.crbkproject.domain.service.SoilMoistureService;
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
