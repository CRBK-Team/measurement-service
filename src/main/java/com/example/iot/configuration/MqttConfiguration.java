package com.example.iot.configuration;

import com.example.iot.domain.EventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import static java.util.Optional.ofNullable;

@Configuration
@IntegrationComponentScan
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class MqttConfiguration {
    MqttClient mqttClient;
    ApplicationEventPublisher publisher;

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                mqttClient.getUrl(), mqttClient.getClientId(), mqttClient.getTopics().toArray(new String[0]));
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            try {
                Object event = castEvent(message);
                publisher.publishEvent(event);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        };
    }

    public <T> T castEvent(Message<?> message) throws JsonProcessingException {
        String topic = ofNullable(message.getHeaders().get("mqtt_receivedTopic"))
                .map(Object::toString)
                .orElseThrow(RuntimeException::new);

        Class<?> clazz = EventType.of(topic).getClazz();

        @SuppressWarnings("unchecked")
        T event = (T) clazz.cast(new ObjectMapper().readValue(message.getPayload().toString(), clazz));

        return event;
    }
}
