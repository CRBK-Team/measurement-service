package net.ddns.crbkproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
class MeasurementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeasurementServiceApplication.class, args);
    }
}
