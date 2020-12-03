package com.example.iot.domain.event;

import java.util.UUID;

public abstract class Event {
    UUID id;
    Long timestamp;

    public void assignHeaders(UUID id, Long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }
}
