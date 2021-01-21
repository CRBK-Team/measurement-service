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

@Document("weather")
@TypeAlias("Weather")
public class Weather {

    @Id
    private final ObjectId id;

    @NotBlank
    private final String sensor;

    @Min(-50)
    @Max(100)
    private final int temp;

    @Min(0)
    @Max(100)
    private final int humidity;

    @Min(200)
    @Max(1200)
    private final int pressure;

    @PastOrPresent
    private final LocalDateTime measuredAt;

    public Weather(String sensor, int temp, int humidity, int pressure, LocalDateTime measuredAt) {
        this.id = new ObjectId();
        this.sensor = sensor;
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.measuredAt = measuredAt;
    }

    @PersistenceConstructor
    public Weather(ObjectId id, String sensor, int temp, int humidity, int pressure, LocalDateTime measuredAt) {
        this.id = id;
        this.sensor = sensor;
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.measuredAt = measuredAt;
    }

    public ObjectId getId() {
        return id;
    }

    public String getSensor() {
        return sensor;
    }

    public int getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return temp == weather.temp && humidity == weather.humidity && pressure == weather.pressure && Objects.equals(id, weather.id) &&
                Objects.equals(sensor, weather.sensor) && Objects.equals(measuredAt, weather.measuredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sensor, temp, humidity, pressure, measuredAt);
    }
}
