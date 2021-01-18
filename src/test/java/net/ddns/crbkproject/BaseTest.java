package net.ddns.crbkproject;

import net.ddns.crbkproject.api.SoilMoistureApi;
import net.ddns.crbkproject.domain.service.SoilMoistureService;
import net.ddns.crbkproject.infrastructure.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
abstract class BaseTest {

    @Autowired
    EventHandler eventHandler;

    @Autowired
    ConversionService mvcConversionService;

    @Autowired
    SoilMoistureApi soilMoistureApi;

    @Autowired
    SoilMoistureService soilMoistureService;
}
