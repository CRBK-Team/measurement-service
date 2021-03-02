package net.ddns.crbkproject.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.rabbitmq")
class AmqpProperties {
    private final String measurementExchange;
    private final String measuredRoutingKey;
    private final String measuredQueue;

    public AmqpProperties(String measurementExchange, String measuredRoutingKey, String measuredQueue) {
        this.measurementExchange = measurementExchange;
        this.measuredRoutingKey = measuredRoutingKey;
        this.measuredQueue = measuredQueue;
    }

    public String getMeasurementExchange() {
        return measurementExchange;
    }

    public String getMeasuredRoutingKey() {
        return measuredRoutingKey;
    }

    public String getMeasuredQueue() {
        return measuredQueue;
    }
}
