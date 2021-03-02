package net.ddns.crbkproject.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.ddns.crbkproject.application.SoilMoistureService;
import net.ddns.crbkproject.domain.model.common.Measurement;
import net.ddns.crbkproject.domain.model.measurement.SoilMoisture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class MeasurementHandler {
    private final ApplicationEventPublisher publisher;
    private final SoilMoistureService soilMoistureService;
    private final ObjectMapper objectMapper;
    private static final Logger LOG = LoggerFactory.getLogger(MeasurementHandler.class);

    public MeasurementHandler(ApplicationEventPublisher publisher, SoilMoistureService soilMoistureService, ObjectMapper objectMapper) {
        this.publisher = publisher;
        this.soilMoistureService = soilMoistureService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "${spring.rabbitmq.measured-queue}")
    void handleMeasurement(@Payload String payload) {
        try {
            Set<Measurement> measurementSet = castMeasurement(payload);
            measurementSet.forEach(publisher::publishEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventListener(condition = "#measurement.type.name() eq 'SOIL_MOISTURE'")
    public void handleSoilMoistureMeasure(Measurement measurement) {
        SoilMoisture soilMoisture = soilMoistureService.add(measurement);
        LOG.info("Received soil moisture measure: {}%", soilMoisture.percent().value());
    }

    @SuppressWarnings("unchecked")
    public Set<Measurement> castMeasurement(String payload) throws JsonProcessingException {
        Map<String, Object> attributes = objectMapper.readValue(payload, Map.class);

        return Measurement.getMeasurementSet(attributes);
    }
}
