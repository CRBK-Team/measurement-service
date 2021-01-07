package com.example.iot;

import com.example.iot.api.SoilMoistureApi;
import com.example.iot.domain.repository.SoilMoistureRepository;
import com.example.iot.domain.service.MeasureService;
import com.example.iot.infrastructure.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

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

    @Autowired
    SoilMoistureRepository soilMoistureRepository;
}
