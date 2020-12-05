package com.example.iot.domain.event;

import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;

@FieldDefaults(level = PROTECTED)
@Getter
public abstract class Event {
    UUID id;
    Long timestamp;

    public void assignHeaders(UUID id, Long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }
}
