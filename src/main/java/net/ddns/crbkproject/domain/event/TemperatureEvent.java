package net.ddns.crbkproject.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureEvent extends Event {
    private String device;
    private int temp;

    private TemperatureEvent() {
    }

    @JsonCreator
    public TemperatureEvent(
            @JsonProperty("dev") String device,
            @JsonProperty("temp") int temp) {
        this.device = device;
        this.temp = temp;
    }

    public String getDevice() {
        return device;
    }

    public int getTemp() {
        return temp;
    }
}
