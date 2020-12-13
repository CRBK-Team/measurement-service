package com.example.iot.domain.event;

public class TemperatureEvent {
    private String sensor;
    private int raw;

    private TemperatureEvent() {
    }

    public TemperatureEvent(String sensor, int raw) {
        this.sensor = sensor;
        this.raw = raw;
    }

    public String getSensor() {
        return sensor;
    }

    public int getRaw() {
        return raw;
    }
}
