package com.example.iot.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.data.elasticsearch.annotations.DateFormat.date_hour_minute_second;
import static org.springframework.data.elasticsearch.annotations.FieldType.Date;

@Document(indexName = "soil-moisture")
@TypeAlias("SoilMoisture")
public class SoilMoisture {

    @Id
    private final UUID id;
    private final String sensor;
    private final int raw;
    private final double percent;
    @Field(type = Date, format = date_hour_minute_second)
    private final LocalDateTime timestamp;

    public SoilMoisture(UUID id, String sensor, int raw, double percent, LocalDateTime timestamp) {
        this.id = id;
        this.sensor = sensor;
        this.raw = raw;
        this.percent = percent;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public String getSensor() {
        return sensor;
    }

    public int getRaw() {
        return raw;
    }

    public double getPercent() {
        return percent;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
