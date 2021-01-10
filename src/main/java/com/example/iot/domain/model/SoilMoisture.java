package com.example.iot.domain.model;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class SoilMoisture {

    @NotBlank
    private final String sensor;
    @Min(0)
    @Max(100)
    private final int percent;
    @Min(0)
    @Max(9999)
    private final int millivolt;
    @PastOrPresent
    private final LocalDateTime timestamp;

    public SoilMoisture(String sensor, int percent, int millivolt, LocalDateTime timestamp) {
        this.sensor = sensor;
        this.percent = percent;
        this.millivolt = millivolt;
        this.timestamp = timestamp;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
