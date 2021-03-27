package net.ddns.crbkproject.api.soil.moisture;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.ddns.crbkproject.domain.model.measurement.SoilMoisture;

import java.time.LocalDateTime;

class SoilMoistureResponse {
    private final String device;
    private final Integer percent;
    private final LocalDateTime measuredAt;

    private SoilMoistureResponse(String device, Integer percent, LocalDateTime measuredAt) {
        this.device = device;
        this.percent = percent;
        this.measuredAt = measuredAt;
    }

    public static SoilMoistureResponse of(SoilMoisture soilMoisture) {
        return new SoilMoistureResponse(
                soilMoisture.device().name(),
                soilMoisture.percent().value(),
                soilMoisture.measuredAt().timestamp());
    }

    @JsonProperty("device")
    public String device() {
        return device;
    }

    @JsonProperty("property")
    public Integer percent() {
        return percent;
    }

    @JsonProperty("measuredAt")
    public LocalDateTime measuredAt() {
        return measuredAt;
    }
}
