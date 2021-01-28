package net.ddns.crbkproject.domain.event;

import java.util.UUID;

public abstract class Event {
    protected UUID id;
    protected Long timestamp;

    public Event assignHeaders(UUID id, Long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
