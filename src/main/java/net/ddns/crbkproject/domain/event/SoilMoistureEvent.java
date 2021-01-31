package net.ddns.crbkproject.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SoilMoistureEvent extends Event {
    private String device;
    private int sm;

    private SoilMoistureEvent() {
    }

    @JsonCreator
    public SoilMoistureEvent(
            @JsonProperty("dev") String device,
            @JsonProperty("sm") int sm) {
        this.device = device;
        this.sm = sm;
    }

    public String getDevice() {
        return device;
    }

    public int getSm() {
        return sm;
    }
}
