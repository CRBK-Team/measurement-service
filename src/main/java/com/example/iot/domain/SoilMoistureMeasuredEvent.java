package com.example.iot.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class SoilMoistureMeasuredEvent {
    String sensorName;
    int rawValue;
    int percentageValue;
    Long timestamp;
}
