package net.ddns.crbkproject.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Event {
    protected final UUID id;
    protected final LocalDateTime timestamp;

    Event() {
        this.id = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
