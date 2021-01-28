package net.ddns.crbkproject.domain.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EventType {
    SOIL_MOISTURE("sm", SoilMoistureEvent.class),
    TEMPERATURE("temp", TemperatureEvent.class);

    String name;
    Class<?> clazz;
    Map<String, String> attributes;

    EventType(String name, Class<?> clazz) {
        this.name = name;
        this.clazz = clazz;
        this.attributes = new HashMap<>();
    }

    public static EventType of(Map.Entry<String, String> entry) {
        return Stream.of(values())
                .filter(value -> value.getName().equalsIgnoreCase(entry.getKey()) || "dev".equalsIgnoreCase(entry.getKey()))
                .map(eventType -> eventType.assignAttribute(entry))
                .findAny()
                .orElse(null);
    }

    public static Set<EventType> getEventTypes(Map<String, String> attributes) {
        return attributes.entrySet().stream()
                .map(EventType::of)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public String getName() {
        return name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    private EventType assignAttribute(Map.Entry<String, String> entry) {
        this.attributes.put(entry.getKey(), entry.getValue());
        return this;
    }
}
