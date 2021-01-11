package com.example.iot.domain.event;

import java.util.stream.Stream;

public enum EventType {
    SOIL_MOISTURE_SENSOR("soil-moisture", SoilMoistureEvent.class),
    TEMPERATURE_SENSOR("temperature", TemperatureEvent.class);

    String name;
    Class<?> clazz;

    EventType(String name, Class<?> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public static EventType of(String name) {
        return Stream.of(values())
                .filter(value -> value.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    public String getName() {
        return name;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
