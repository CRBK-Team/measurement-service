package net.ddns.crbkproject.domain.model.measurement;

import net.ddns.crbkproject.infrastructure.exception.DomainException;
import net.ddns.crbkproject.domain.model.common.ExceptionCode;

import java.util.Objects;

public class Percent {
    private final int value;

    private Percent(int value) {
        this.value = value;
    }

    public static Percent of(int value) {
        validate(value);
        return new Percent(value);
    }

    private static void validate(int value) {
        if (value < 0 || value > 100) {
            throw new DomainException(ExceptionCode.NOT_VALIDATED_MEASURE);
        }
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Percent percent = (Percent) o;
        return value == percent.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
