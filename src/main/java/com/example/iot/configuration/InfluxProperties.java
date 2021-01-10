package com.example.iot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("integration.influx")
@ConstructorBinding
public class InfluxProperties {
    private final String url;
    private final String token;

    public InfluxProperties(String url, String token) {
        this.url = url;
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }
}
