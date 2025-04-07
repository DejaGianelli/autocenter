package com.drivelab.autocenter.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemDetails {

    private final LocalDateTime timestamp;

    private final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<InvalidParam> invalidParams;

    public ProblemDetails(String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.invalidParams = new ArrayList<>();
    }

    public ProblemDetails add(InvalidParam invalidParams) {
        this.invalidParams.add(invalidParams);
        return this;
    }

    public List<InvalidParam> getInvalidParams() {
        return invalidParams;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public static class InvalidParam {
        private final String name;
        private Object rejectedValue;
        private final String reason;

        public InvalidParam(String name, String reason) {
            this.name = name;
            this.reason = reason;
        }

        public InvalidParam setRejectedValue(@Nullable Object rejectedValue) {
            this.rejectedValue = rejectedValue;
            return this;
        }

        public @Nullable String getRejectedValue() {
            return rejectedValue == null ? null : rejectedValue.toString();
        }

        public String getName() {
            return name;
        }

        public String getReason() {
            return reason;
        }
    }
}
