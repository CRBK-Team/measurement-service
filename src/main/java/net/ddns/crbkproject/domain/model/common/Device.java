package net.ddns.crbkproject.domain.model.common;

import net.ddns.crbkproject.domain.exception.DomainException;
import net.ddns.crbkproject.domain.exception.ExceptionCode;

import java.util.Objects;

public class Device {
    private final String name;

    private Device(String name) {
        this.name = name;
    }

    public static Device of(String name) {
        validate(name);
        return new Device(name);
    }

    private static void validate(String name) {
        int nameLength = name.length();
        if (nameLength < 3 || nameLength > 30) {
            throw new DomainException(ExceptionCode.NOT_VALIDATED_MEASURE);
        }
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(name, device.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
