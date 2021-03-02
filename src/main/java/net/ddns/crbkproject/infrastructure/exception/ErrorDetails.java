package net.ddns.crbkproject.infrastructure.exception;

import java.util.Objects;

class ErrorDetails {
    private final String code;
    private final String message;

    public ErrorDetails(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorDetails(DomainException exception) {
        this.code = Objects.toString(exception.code().code());
        this.message = exception.getMessage();
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
