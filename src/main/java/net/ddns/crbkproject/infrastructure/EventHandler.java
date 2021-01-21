package net.ddns.crbkproject.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.ddns.crbkproject.domain.event.Event;
import net.ddns.crbkproject.domain.event.EventType;
import net.ddns.crbkproject.domain.event.SoilMoistureEvent;
import net.ddns.crbkproject.domain.event.WeatherEvent;
import net.ddns.crbkproject.domain.service.SoilMoistureService;
import net.ddns.crbkproject.domain.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class EventHandler {
    private final SoilMoistureService soilMoistureService;
    private final WeatherService weatherService;
    private static final Logger log = LoggerFactory.getLogger(EventHandler.class);

    public EventHandler(SoilMoistureService soilMoistureService, WeatherService weatherService) {
        this.soilMoistureService = soilMoistureService;
        this.weatherService = weatherService;
    }

    @EventListener(SoilMoistureEvent.class)
    public void handleSoilMoistureMeasure(SoilMoistureEvent event) {
        soilMoistureService.addSoilMoisture(event);
        log.info("Received soil moisture measure: {}%", event.getPct());
    }

    @EventListener(WeatherEvent.class)
    public void handleWeatherMeasure(WeatherEvent event) {
        weatherService.addWeather(event);
        log.info("Received weather measure: {} C degrees, {} %, {} hPa ", event.getTemp(), event.getHum(), event.getPre());
    }

    @SuppressWarnings("unchecked")
    public static <T> T castEvent(Message<?> message) throws JsonProcessingException {
        String topic = ofNullable(message.getHeaders().get("mqtt_receivedTopic"))
                .map(Object::toString)
                .orElseThrow(RuntimeException::new);

        Class<?> clazz = EventType.of(topic).getClazz();

        Event event = (Event) clazz.cast(new ObjectMapper().readValue(message.getPayload().toString(), clazz));
        event.assignHeaders(message.getHeaders().getId(), message.getHeaders().getTimestamp());
        return (T) event;
    }
}
