package net.ddns.crbkproject.integration;

import net.ddns.crbkproject.application.SoilMoistureService;
import net.ddns.crbkproject.infrastructure.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
abstract class BaseIntegrationTest {

    @Autowired
    EventHandler eventHandler;

    @Autowired
    ConversionService mvcConversionService;

    @Autowired
    SoilMoistureService soilMoistureService;
}
