package net.ddns.crbkproject.infrastructure.exception;

import java.time.Instant;
import java.util.List;

class ExceptionError {
    private final Instant timestamp;
    private final String path;
    private final List<ErrorDetails> errors;

    public ExceptionError(String path, List<ErrorDetails> errors) {
        this.timestamp = Instant.now();
        this.path = path;
        this.errors = errors;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public List<ErrorDetails> getErrors() {
        return errors;
    }
}
