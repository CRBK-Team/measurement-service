package com.example.iot;

import com.example.iot.api.SoilMoistureApi;
import com.example.iot.domain.service.MeasureService;
import com.example.iot.infrastructure.EventHandler;
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
    @Qualifier("mvcConversionService")
    ConversionService conversionService;

    @Autowired
    SoilMoistureApi soilMoistureApi;

    @Autowired
    MeasureService measureService;
}
