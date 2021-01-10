package com.example.iot.domain.model;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

@Measurement(name = "soil-moisture")
public class SoilMoistureRawInflux {

    @Column(tag = true)
    private String sensor;

    @Column(name = "_value")
    private Long percent;

    @Column(tag = true)
    private String voltage;

    @Column(name = "_time", timestamp = true)
    private Instant timestamp;

    // For deserialization use only
    public SoilMoistureRawInflux() {
    }

    public SoilMoistureRawInflux(String sensor, Long percent, String voltage, Instant timestamp) {
        this.sensor = sensor;
        this.percent = percent;
        this.voltage = voltage;
        this.timestamp = timestamp;
    }

    public String getSensor() {
        return sensor;
    }

    public Long getPercent() {
        return percent;
    }

    public String getVoltage() {
        return voltage;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
