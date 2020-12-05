package com.example.iot;

import com.example.iot.infrastructure.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

@SpringBootTest
class BaseTest {

    @Autowired
    EventHandler eventHandler;

    @Autowired
    @Qualifier("mvcConversionService")
    ConversionService conversionService;
}
