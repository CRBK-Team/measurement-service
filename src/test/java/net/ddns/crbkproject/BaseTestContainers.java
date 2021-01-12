package net.ddns.crbkproject;

import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers(disabledWithoutDocker = true)
public abstract class BaseTestContainers extends BaseTest {

    static final DockerComposeContainer MQTT_CONTAINER;

    static {
        MQTT_CONTAINER = new DockerComposeContainer(new File("src/test/resources/mosquitto.yml"));
        MQTT_CONTAINER.start();
    }
}
