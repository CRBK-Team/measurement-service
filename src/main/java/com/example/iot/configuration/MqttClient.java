package com.example.iot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Set;

@ConfigurationProperties("integration.mqtt")
@ConstructorBinding
public class MqttClient {
    private final String url;
    private final String clientId;
    private final Set<String> topics;

    public MqttClient(String url, String clientId, Set<String> topics) {
        this.url = url;
        this.clientId = clientId;
        this.topics = topics;
    }

    public String getUrl() {
        return url;
    }

    public String getClientId() {
        return clientId;
    }

    public Set<String> getTopics() {
        return topics;
    }
}
