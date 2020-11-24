package com.example.iot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Stream;

import static com.example.iot.domain.Event.EventType.SOIL_MOISTURE_SENSOR;
import static com.example.iot.domain.Event.EventType.TEMPERATURE_SENSOR;

public abstract class Event {

    @AllArgsConstructor
    @Getter
    public enum EventType {
        SOIL_MOISTURE_SENSOR("soil-moisture-sensor", SoilMoistureMeasuredEvent.class),
        TEMPERATURE_SENSOR("temperature-sensor", TemperatureMeasuredEvent.class);

        String name;
        Class clazz;

        public static EventType of(String name) {
            return Stream.of(values())
                    .filter(value -> value.getName().equalsIgnoreCase(name))
                    .findAny()
                    .orElseThrow(RuntimeException::new);
        }
    }
}
