package com.example.iot.domain.model;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(indexName = "soil-moisture-measure")
@TypeAlias("SoilMoistureMeasure")
@Value
public class SoilMoisture {

    @Id
    UUID id;
    String sensor;
    int raw;
    double percent;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    LocalDateTime timestamp;

    public SoilMoisture(String sensor, int raw, double percent) {
        this.id = UUID.randomUUID();
        this.sensor = sensor;
        this.raw = raw;
        this.percent = percent;
        this.timestamp = LocalDateTime.now(Clock.systemUTC());
    }
}
