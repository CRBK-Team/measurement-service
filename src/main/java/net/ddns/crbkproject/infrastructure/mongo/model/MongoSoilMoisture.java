package net.ddns.crbkproject.infrastructure.mongo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Document("soil-moisture")
@TypeAlias("SoilMoisture")
public class MongoSoilMoisture {

    @Id
    private final ObjectId id;
    private final String device;
    private final Integer percent;
    private final LocalDateTime measuredAt;

    @PersistenceConstructor
    private MongoSoilMoisture(ObjectId id, String device, int percent, LocalDateTime measuredAt) {
        this.id = id;
        this.device = device;
        this.percent = percent;
        this.measuredAt = measuredAt;
    }

    public static MongoSoilMoisture of(String device, int percent, LocalDateTime measuredAt) {
        return new MongoSoilMoisture(ObjectId.get(), device, percent, measuredAt);
    }

    public ObjectId id() {
        return id;
    }

    public String device() {
        return device;
    }

    public int percent() {
        return percent;
    }

    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MongoSoilMoisture that = (MongoSoilMoisture) o;
        return Objects.equals(id, that.id) && Objects.equals(device, that.device) && Objects.equals(percent, that.percent) && Objects.equals(measuredAt, that.measuredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, device, percent, measuredAt);
    }
}
