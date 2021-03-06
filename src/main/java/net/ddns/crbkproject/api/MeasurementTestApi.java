package net.ddns.crbkproject.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/v1/measurements")
@Profile("test")
public class MeasurementTestApi {
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    public MeasurementTestApi(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public void process(@RequestBody Object body) {
        try {
            String payload = objectMapper.writeValueAsString(body);
            Message message = MessageBuilder
                    .withBody(payload.getBytes(StandardCharsets.UTF_8))
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();
            rabbitTemplate.convertAndSend("measurement", "measured", message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
