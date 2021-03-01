package net.ddns.crbkproject.domain.model.common;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Event {
    private final LocalDateTime timestamp;
    private final EventType type;
    private final Map<String, Object> attributes;

    private Event(LocalDateTime timestamp, EventType type, Map<String, Object> attributes) {
        this.timestamp = timestamp;
        this.type = type;
        this.attributes = attributes;
    }

    public static Event of(EventType type) {
        return new Event(LocalDateTime.now(), type, new HashMap<>());
    }

    public static Set<Event> getEvents(Map<String, Object> attributes) {
        Set<Event> events = new HashSet<>();
        EventType.toSet(attributes.keySet()).forEach(
                type -> {
                    Event event = Event.of(type);
                    type.properties.forEach(
                            property -> attributes.entrySet().stream()
                                    .filter(entry -> entry.getKey().equalsIgnoreCase(property))
                                    .forEach(event::assignAttribute));
                    events.add(event);
                });
        return events;
    }

    public Event assignAttribute(Map.Entry<String, Object> entry) {
        this.attributes.put(entry.getKey(), entry.getValue());
        return this;
    }

    public LocalDateTime timestamp() {
        return timestamp;
    }

    public EventType type() {
        return type;
    }

    public Map<String, Object> attributes() {
        return attributes;
    }
}
