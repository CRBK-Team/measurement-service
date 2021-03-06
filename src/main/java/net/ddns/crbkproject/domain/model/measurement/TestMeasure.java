package net.ddns.crbkproject.domain.model.measurement;

import java.time.LocalDateTime;
import java.util.Objects;

public class TestMeasure {
    private final String id;
    private final Integer humidity;
    private final Integer voltage;
    private final LocalDateTime measuredAt;

    private TestMeasure(String id, Integer humidity, Integer voltage, LocalDateTime measuredAt) {
        this.id = id;
        this.humidity = humidity;
        this.voltage = voltage;
        this.measuredAt = measuredAt;
    }

    public static TestMeasure of(String id, int humidity, int voltage, LocalDateTime measuredAt) {
        return new TestMeasure(id, humidity, voltage, measuredAt);
    }

    public String id() {
        return id;
    }

    public Integer humidity() {
        return humidity;
    }

    public Integer voltage() {
        return voltage;
    }

    public LocalDateTime measuredAt() {
        return measuredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestMeasure that = (TestMeasure) o;
        return Objects.equals(id, that.id) && Objects.equals(humidity, that.humidity) && Objects.equals(voltage, that.voltage) && Objects.equals(measuredAt, that.measuredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, humidity, voltage, measuredAt);
    }
}
