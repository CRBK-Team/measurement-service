package com.example.iot;

import org.assertj.core.api.Assertions;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.File;
import java.io.IOException;

@Testcontainers(disabledWithoutDocker = true)
public abstract class BaseTestContainers extends BaseTest {

    static final ElasticsearchContainer ELASTICSEARCH_CONTAINER;
    static final DockerComposeContainer MQTT_CONTAINER;

    static {
        ELASTICSEARCH_CONTAINER = new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:7.9.3");
        ELASTICSEARCH_CONTAINER.start();
    }

    static {
        MQTT_CONTAINER = new DockerComposeContainer(new File("src/test/resources/mosquitto.yml"));
        MQTT_CONTAINER.start();
    }
}
