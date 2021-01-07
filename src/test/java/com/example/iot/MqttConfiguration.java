package com.example.iot;

import com.example.iot.configuration.MqttClient;
import com.example.iot.domain.event.Event;
import com.example.iot.domain.event.EventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class MqttConfiguration {
    private final MqttClient mqttClient;
    private final ApplicationEventPublisher publisher;

    public MqttConfiguration(MqttClient mqttClient, ApplicationEventPublisher publisher) {
        this.mqttClient = mqttClient;
        this.publisher = publisher;
    }

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
