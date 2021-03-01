package net.ddns.crbkproject.domain.model.common;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EventType {
    SOIL_MOISTURE(Set.of("dev", "sm")),
    TEMPERATURE(Set.of("dev", "temp"));

    Set<String> properties;

    EventType(Set<String> properties) {
        this.properties = properties;
    }

    public static Set<EventType> toSet(Set<String> keys) {
        return Stream.of(EventType.values())
                .filter(value -> keys.containsAll(value.properties))
                .collect(Collectors.toSet());
    }
}
