package net.ddns.crbkproject.domain.model.common;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MeasurementType {
    SOIL_MOISTURE(Set.of("dev", "sm")),
    TEMPERATURE(Set.of("dev", "temp"));

    Set<String> properties;

    MeasurementType(Set<String> properties) {
        this.properties = properties;
    }

    public static Set<MeasurementType> toSet(Set<String> keys) {
        return Stream.of(MeasurementType.values())
                .filter(value -> keys.containsAll(value.properties))
                .collect(Collectors.toSet());
    }
}
