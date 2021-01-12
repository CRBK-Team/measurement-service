package net.ddns.crbkproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CrbkProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrbkProjectApplication.class, args);
    }
}
