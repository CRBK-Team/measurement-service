package com.example.iot.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
public enum EventType {
    SOIL_MOISTURE_SENSOR("soil-moisture-sensor"),
    TEMPERATURE_SENSOR("temperature-sensor");

    String name;
}
