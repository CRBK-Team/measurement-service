package net.ddns.crbkproject.domain.model.measurement;

import net.ddns.crbkproject.domain.exception.DomainException;
import net.ddns.crbkproject.domain.exception.ExceptionCode;

import java.time.LocalDateTime;
import java.util.Objects;

public class MeasuredAt {
    private final LocalDateTime timestamp;

    private MeasuredAt(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static MeasuredAt of(LocalDateTime timestamp) {
        validate(timestamp);
        return new MeasuredAt(timestamp);
    }

    private static void validate(LocalDateTime timestamp) {
        if (timestamp.isAfter(LocalDateTime.now())) {
            throw new DomainException(ExceptionCode.NOT_VALIDATED_MEASURE);
        }
    }

    public LocalDateTime timestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasuredAt that = (MeasuredAt) o;
        return Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp);
    }
}
