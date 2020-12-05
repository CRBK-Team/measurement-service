package com.example.iot.domain.model;

import lombok.Value;
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
@Value
public class SoilMoisture {

    @Id
    UUID id;
    String sensor;
    int raw;
    double percent;
    @Field(type = Date, format = date_hour_minute_second)
    LocalDateTime timestamp;
}
