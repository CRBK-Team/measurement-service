package net.ddns.crbkproject.infrastructure.mongo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Document("test-measure")
@TypeAlias("TestMeasure")
public class MongoTestMeasure {

    @Id
    private final ObjectId id;
    private final Integer humidity;
    private final Integer voltage;
    private final LocalDateTime measuredAt;

    @PersistenceConstructor
    private MongoTestMeasure(ObjectId id, Integer humidity, Integer voltage, LocalDateTime measuredAt) {
        this.id = id;
        this.humidity = humidity;
        this.voltage = voltage;
        this.measuredAt = measuredAt;
    }

    public static MongoTestMeasure of(int humidity, int voltage, LocalDateTime measuredAt) {
        return new MongoTestMeasure(ObjectId.get(), humidity, voltage, measuredAt);
    }

    public ObjectId id() {
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
        MongoTestMeasure that = (MongoTestMeasure) o;
        return Objects.equals(id, that.id) && Objects.equals(humidity, that.humidity) && Objects.equals(voltage, that.voltage) && Objects.equals(measuredAt, that.measuredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, humidity, voltage, measuredAt);
    }
}
