package net.ddns.crbkproject.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.ddns.crbkproject.domain.event.Event;
import net.ddns.crbkproject.infrastructure.EventHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.Set;

@Configuration
@IntegrationComponentScan
@Profile("!test")
public class MqttConfiguration {
    private final MqttProperties properties;
    private final ApplicationEventPublisher publisher;

    public MqttConfiguration(MqttProperties properties, ApplicationEventPublisher publisher) {
        this.properties = properties;
        this.publisher = publisher;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                properties.getUrl(), properties.getClientId(), properties.getTopics().toArray(new String[0]));
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
                Set<? extends Event> events = EventHandler.castEvents(message);
                events.forEach(publisher::publishEvent);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        };
    }
}
