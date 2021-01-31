package net.ddns.crbkproject.domain.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Objects;

@Document("soil-moisture")
@TypeAlias("SoilMoisture")
public class SoilMoisture {

    @Id
    private final ObjectId id;

    @NotBlank
    private final String device;

    @Min(0)
    @Max(100)
    private final int percent;

    @PastOrPresent
    private final LocalDateTime measuredAt;

    public SoilMoisture(String device, int percent, LocalDateTime measuredAt) {
        this.id = new ObjectId();
        this.device = device;
        this.percent = percent;
        this.measuredAt = measuredAt;
    }

    @PersistenceConstructor
    public SoilMoisture(ObjectId id, String device, int percent, LocalDateTime measuredAt) {
        this.id = id;
        this.device = device;
        this.percent = percent;
        this.measuredAt = measuredAt;
    }

    public ObjectId getId() {
        return id;
    }

    public String getDevice() {
        return device;
    }

    public int getPercent() {
        return percent;
    }

    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoilMoisture that = (SoilMoisture) o;
        return percent == that.percent && Objects.equals(id, that.id) && Objects.equals(device, that.device) && Objects.equals(measuredAt, that.measuredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, device, percent, measuredAt);
    }
}
