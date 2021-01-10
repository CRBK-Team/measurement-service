package com.example.iot.domain.event;

public class SoilMoistureEvent extends Event {
    private String sensor;
    private int pct;
    private int mVolt;

    private SoilMoistureEvent() {
    }

    public SoilMoistureEvent(String sensor, int pct, int mVolt) {
        this.sensor = sensor;
        this.pct = pct;
        this.mVolt = mVolt;
    }

    public String getSensor() {
        return sensor;
    }

    public int getPct() {
        return pct;
    }

    public int getmVolt() {
        return mVolt;
    }
}
