package com.example.iot.domain.event;

public class SoilMoistureEvent extends Event {
    private String sensor;
    private int raw;
    private int pct;

    private SoilMoistureEvent() {
    }

    public SoilMoistureEvent(String sensor, int raw, int pct) {
        this.sensor = sensor;
        this.raw = raw;
        this.pct = pct;
    }

    public String getSensor() {
        return sensor;
    }

    public int getRaw() {
        return raw;
    }

    public int getPct() {
        return pct;
    }
}
