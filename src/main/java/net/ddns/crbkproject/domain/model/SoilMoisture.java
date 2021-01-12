package net.ddns.crbkproject.domain.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Document("soil-moisture")
@TypeAlias("SoilMoisture")
public class SoilMoisture {

    @Id
    private final ObjectId id;

    @NotBlank
    private final String sensor;

    @Min(0)
    @Max(100)
    private final int percent;

    @Min(0)
    @Max(9999)
    private final int millivolt;

    @PastOrPresent
    private final LocalDateTime measuredAt;

    public SoilMoisture(String sensor, int percent,  int millivolt, LocalDateTime measuredAt) {
        this.id = new ObjectId();
        this.sensor = sensor;
        this.percent = percent;
        this.millivolt = millivolt;
        this.measuredAt = measuredAt;
    }

    @PersistenceConstructor
    public SoilMoisture(ObjectId id, String sensor, int percent, int millivolt, LocalDateTime measuredAt) {
        this.id = id;
        this.sensor = sensor;
        this.percent = percent;
        this.millivolt = millivolt;
        this.measuredAt = measuredAt;
    }

    public ObjectId getId() {
        return id;
    }

    public String getSensor() {
        return sensor;
    }

    public int getPercent() {
        return percent;
    }

    public int getMillivolt() {
        return millivolt;
    }

    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }
}
