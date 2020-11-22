package com.example.iot.configuration;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Set;

@ConfigurationProperties("integration.mqtt")
@ConstructorBinding
@Value
public class MqttClient {
    String url;
    String clientId;
    Set<String> topics;
}
