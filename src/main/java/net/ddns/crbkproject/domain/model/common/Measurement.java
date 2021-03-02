package net.ddns.crbkproject.domain.model.common;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Measurement {
    private final LocalDateTime timestamp;
    private final MeasurementType type;
    private final Map<String, Object> attributes;

    private Measurement(LocalDateTime timestamp, MeasurementType type, Map<String, Object> attributes) {
        this.timestamp = timestamp;
        this.type = type;
        this.attributes = attributes;
    }

    public static Measurement of(MeasurementType type) {
        return new Measurement(LocalDateTime.now(), type, new HashMap<>());
    }

    public static Set<Measurement> getMeasurementSet(Map<String, Object> attributes) {
        Set<Measurement> measurementSet = new HashSet<>();
        MeasurementType.toSet(attributes.keySet()).forEach(
                type -> {
                    Measurement measurement = Measurement.of(type);
                    type.properties.forEach(
                            property -> attributes.entrySet().stream()
                                    .filter(entry -> entry.getKey().equalsIgnoreCase(property))
                                    .forEach(measurement::assignAttribute));
                    measurementSet.add(measurement);
                });
        return measurementSet;
    }

    public Measurement assignAttribute(Map.Entry<String, Object> entry) {
        this.attributes.put(entry.getKey(), entry.getValue());
        return this;
    }

    public LocalDateTime timestamp() {
        return timestamp;
    }

    public MeasurementType type() {
        return type;
    }

    public Map<String, Object> attributes() {
        return attributes;
    }
}
