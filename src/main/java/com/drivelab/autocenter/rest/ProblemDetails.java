package com.drivelab.autocenter.rest;

import java.time.LocalDateTime;

public class ProblemDetails {
    private final LocalDateTime timestamp;
    private final String message;

    public ProblemDetails(String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
