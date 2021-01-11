package com.example.iot.domain.exception;

import java.util.Objects;

public class ErrorDetails {
    private final String code;
    private final String message;

    public ErrorDetails(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorDetails(DomainException exception) {
        this.code = Objects.toString(exception.getCode().getCode());
        this.message = exception.getMessage();
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
