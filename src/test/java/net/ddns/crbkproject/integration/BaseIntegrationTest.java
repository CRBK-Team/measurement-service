package net.ddns.crbkproject.integration;

import net.ddns.crbkproject.application.SoilMoistureService;
import net.ddns.crbkproject.infrastructure.MeasurementHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {

    @Autowired
    protected MeasurementHandler measurementHandler;

    @Autowired
    protected ConversionService mvcConversionService;

    @Autowired
    protected SoilMoistureService soilMoistureService;
}
