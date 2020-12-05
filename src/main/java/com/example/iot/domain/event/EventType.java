package com.example.iot.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum EventType {
    SOIL_MOISTURE_SENSOR("soil-moisture-sensor", SoilMoistureEvent.class),
    TEMPERATURE_SENSOR("temperature-sensor", TemperatureEvent.class);

    String name;
    Class<?> clazz;

    public static EventType of(String name) {
        return Stream.of(values())
                .filter(value -> value.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }
}
