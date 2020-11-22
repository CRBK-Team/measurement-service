package com.example.iot.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Value
public class SoilMoistureMeasuredEvent extends GenericEvent {
    String sensorName;
    int rawValue;
    int percentageValue;
    LocalDateTime timestamp;

    public SoilMoistureMeasuredEvent(String type, String sensorName, int rawValue, int percentageValue, LocalDateTime timestamp) {
        super(type);
        this.sensorName = sensorName;
        this.rawValue = rawValue;
        this.percentageValue = percentageValue;
        this.timestamp = timestamp;
    }
}
