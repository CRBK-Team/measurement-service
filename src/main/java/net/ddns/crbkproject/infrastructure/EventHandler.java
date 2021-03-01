package net.ddns.crbkproject.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.ddns.crbkproject.application.SoilMoistureService;
import net.ddns.crbkproject.domain.model.common.Event;
import net.ddns.crbkproject.domain.model.measurement.SoilMoisture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class EventHandler {
    private final SoilMoistureService soilMoistureService;
    public final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(EventHandler.class);

    public EventHandler(SoilMoistureService soilMoistureService, ObjectMapper objectMapper) {
        this.soilMoistureService = soilMoistureService;
        this.objectMapper = objectMapper;
    }

    @EventListener(condition = "#event.type.name() eq 'SOIL_MOISTURE'")
    public void handleSoilMoistureMeasure(Event event) {
        SoilMoisture soilMoisture = soilMoistureService.add(event);
        log.info("Received soil moisture measure: {}%", soilMoisture.percent());
    }

    @SuppressWarnings("unchecked")
    public static Set<Event> castEvents(String payload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> attributes = mapper.readValue(payload, Map.class);

        return Event.getEvents(attributes);
    }
}
