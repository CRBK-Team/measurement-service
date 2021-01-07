package com.example.iot;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.retry.annotation.Retryable;

import static java.util.Objects.requireNonNull;

@Configuration
@Profile("test")
public class ElasticsearchClientConfiguration extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        RestClients.ElasticsearchRestClient client = null;
        try {
            client = RestClients.create(ClientConfiguration.create("127.0.0.1:9200"));
            return client.rest();
        } finally {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
