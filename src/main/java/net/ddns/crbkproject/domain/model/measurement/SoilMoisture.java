package net.ddns.crbkproject.domain.model.measurement;

import java.util.Objects;

public class SoilMoisture {
    private final String id;
    private final Device device;
    private final Percent percent;
    private final MeasuredAt measuredAt;

    private SoilMoisture(String id, Device device, Percent percent, MeasuredAt measuredAt) {
        this.id = id;
        this.device = device;
        this.percent = percent;
        this.measuredAt = measuredAt;
    }

    public static SoilMoisture of(String id, Device device, Percent percent, MeasuredAt measuredAt) {
        return new SoilMoisture(id, device, percent, measuredAt);
    }

    public String id() {
        return id;
    }

    public Device device() {
        return device;
    }

    public Percent percent() {
        return percent;
    }

    public MeasuredAt measuredAt() {
        return measuredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoilMoisture that = (SoilMoisture) o;
        return Objects.equals(id, that.id) && Objects.equals(device, that.device) && Objects.equals(percent, that.percent) && Objects.equals(measuredAt, that.measuredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, device, percent, measuredAt);
    }
}
