package net.ddns.crbkproject.configuration;

import net.ddns.crbkproject.domain.model.Event;
import net.ddns.crbkproject.infrastructure.EventHandler;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Set;

@Configuration
public class AmqpConfiguration {
    private final AmqpProperties properties;
    private final ApplicationEventPublisher publisher;

    public AmqpConfiguration(AmqpProperties properties, ApplicationEventPublisher publisher) {
        this.properties = properties;
        this.publisher = publisher;
    }

    @Bean
    public Declarables measurementBindings() {
        Queue measuredQueue = new Queue(properties.getMeasuredQueue());
        DirectExchange measurementExchange = new DirectExchange(properties.getMeasurementExchange());

        return new Declarables(measuredQueue, measurementExchange,
                BindingBuilder.bind(measuredQueue).to(measurementExchange).with(properties.getMeasuredRoutingKey()));
    }

    @RabbitListener(queues = "${spring.rabbitmq.measured-queue}")
    public void handleMeasurement(@Payload String payload) {
        try {
            Set<Event> events = EventHandler.castEvents(payload);
            events.forEach(publisher::publishEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
