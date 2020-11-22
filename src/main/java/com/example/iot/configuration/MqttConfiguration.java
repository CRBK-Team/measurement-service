package com.example.iot.configuration;

import com.example.iot.domain.GenericEvent;
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
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.util.Optional.ofNullable;

@Configuration
@IntegrationComponentScan
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class MqttConfiguration {
    static String receivedTopic = "mqtt_receivedTopic";
    ApplicationEventPublisher publisher;
    MqttClient mqttClient;

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
            MessageHeaders headers = message.getHeaders();

            publisher.publishEvent(new GenericEvent(getHeaderValue(headers, receivedTopic), message.getPayload().toString()));
            logEvent(headers);
        };
    }

    private void logEvent(MessageHeaders headers) {
        log.info("{} received message: { id: {}, topic: {} }", getTime(headers),
                getHeaderValue(headers, "id"), getHeaderValue(headers, "mqtt_receivedTopic"));
    }

    private String getHeaderValue(MessageHeaders headers, String headerKey) {
        return ofNullable(headers.get(headerKey))
                .map(Object::toString)
                .orElseThrow(RuntimeException::new);
    }

    private String getTime(MessageHeaders headers) {
        long epoch = ofNullable(headers.get("timestamp"))
                .map(timestamp -> Long.parseLong(timestamp.toString()))
                .orElseThrow(RuntimeException::new);

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epoch), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
