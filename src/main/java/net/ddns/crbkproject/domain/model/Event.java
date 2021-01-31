package net.ddns.crbkproject.domain.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Event {
    private final LocalDateTime timestamp;
    private final Event.Type type;
    private final Map<String, Object> attributes;

    public Event(Type type) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.attributes = new HashMap<>();
    }

    public static Set<Event> getEvents(Map<String, Object> attributes) {
        Set<Event> events = new HashSet<>();
        Type.toSet(attributes.keySet()).forEach(
                type -> {
                    Event event = new Event(type);
                    type.properties.forEach(
                            property -> attributes.entrySet().stream()
                                    .filter(entry -> entry.getKey().equalsIgnoreCase(property))
                                    .forEach(event::assignAttribute));
                    events.add(event);
                });
        return events;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Type getType() {
        return type;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    private Event assignAttribute(Map.Entry<String, Object> entry) {
        this.attributes.put(entry.getKey(), entry.getValue());
        return this;
    }

    public enum Type {
        SOIL_MOISTURE(Set.of("dev", "sm")),
        TEMPERATURE(Set.of("dev", "temp"));

        Set<String> properties;

        Type(Set<String> properties) {
            this.properties = properties;
        }

        public static Set<Type> toSet(Set<String> keys) {
            return Stream.of(Type.values())
                    .filter(value -> keys.containsAll(value.properties))
                    .collect(Collectors.toSet());
        }
    }
}
