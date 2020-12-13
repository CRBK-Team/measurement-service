package com.example.iot.configuration;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import static java.util.Objects.requireNonNull;

@Configuration
public class ElasticsearchClientConfiguration extends AbstractElasticsearchConfiguration {

    @Value("${integration.elasticsearch.endpoint}")
    private String endpoint;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        RestClients.ElasticsearchRestClient client = null;
        try {
            client = RestClients.create(ClientConfiguration.create(endpoint));
            return client.rest();
        } finally {
            try {
                requireNonNull(client).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
