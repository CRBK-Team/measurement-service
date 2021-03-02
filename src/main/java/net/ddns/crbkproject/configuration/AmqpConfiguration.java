package net.ddns.crbkproject.configuration;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AmqpConfiguration {
    private final AmqpProperties properties;

    public AmqpConfiguration(AmqpProperties properties) {
        this.properties = properties;
    }

    @Bean
    public Declarables measurementBindings() {
        Queue measuredQueue = new Queue(properties.getMeasuredQueue());
        DirectExchange measurementExchange = new DirectExchange(properties.getMeasurementExchange());

        return new Declarables(measuredQueue, measurementExchange,
                BindingBuilder.bind(measuredQueue).to(measurementExchange).with(properties.getMeasuredRoutingKey()));
    }
}
