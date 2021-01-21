package net.ddns.crbkproject.domain.event;

import net.ddns.crbkproject.domain.exception.DomainException;

import java.util.stream.Stream;

import static net.ddns.crbkproject.domain.exception.ExceptionCode.NOT_SUPPORTED_TOPIC;

public enum EventType {
    SOIL_MOISTURE_SENSOR("soil-moisture", SoilMoistureEvent.class),
    WEATHER_SENSOR("weather", WeatherEvent.class);

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
                .orElseThrow(() -> new DomainException(NOT_SUPPORTED_TOPIC, name));
    }

    public String getName() {
        return name;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
