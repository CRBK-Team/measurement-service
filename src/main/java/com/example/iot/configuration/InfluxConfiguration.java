package com.example.iot.configuration;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxConfiguration {
    private final InfluxProperties properties;

    public InfluxConfiguration(InfluxProperties properties) {
        this.properties = properties;
    }

    @Bean
    public InfluxDBClient influxDBClient() {
        return InfluxDBClientFactory.create(properties.getUrl(), properties.getToken().toCharArray(), "primergy");
    }
}
