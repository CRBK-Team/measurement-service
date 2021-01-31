package net.ddns.crbkproject.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.ddns.crbkproject.domain.event.Event;
import net.ddns.crbkproject.domain.event.EventType;
import net.ddns.crbkproject.domain.event.SoilMoistureEvent;
import net.ddns.crbkproject.domain.exception.DomainException;
import net.ddns.crbkproject.domain.exception.ExceptionCode;
import net.ddns.crbkproject.domain.service.SoilMoistureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EventHandler {
    private final SoilMoistureService soilMoistureService;
    public final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(EventHandler.class);

    public EventHandler(SoilMoistureService soilMoistureService, ObjectMapper objectMapper) {
        this.soilMoistureService = soilMoistureService;
        this.objectMapper = objectMapper;
    }

    @EventListener(SoilMoistureEvent.class)
    public void handleSoilMoistureMeasure(SoilMoistureEvent event) {
        soilMoistureService.addSoilMoisture(event);
        log.info("Received soil moisture measure: {}%", event.getSm());
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> castEvents(String payload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> attributes = mapper.readValue(payload, Map.class);

        Set<EventType> eventTypes = EventType.getEventTypes(attributes);

        return eventTypes.stream()
                .map(eventType -> getEvent(eventType, mapper))
                .map(event -> (T) event)
                .collect(Collectors.toSet());
    }

    private static Event getEvent(EventType eventType, ObjectMapper mapper) {
        Class<?> clazz = eventType.getClazz();

        try {
            String json = mapper.writeValueAsString(eventType.getAttributes());
            return (Event) clazz.cast(mapper.readValue(json, clazz));
        } catch (JsonProcessingException e) {
            throw new DomainException(ExceptionCode.NOT_SUPPORTED_MEASURE, eventType.getName());
        }
    }
}
