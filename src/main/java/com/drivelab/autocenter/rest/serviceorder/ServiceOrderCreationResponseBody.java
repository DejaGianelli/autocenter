package com.drivelab.autocenter.rest.serviceorder;

import java.time.OffsetDateTime;

public class ServiceOrderCreationResponseBody {

    private String id;
    private String status;
    private String description;
    private OffsetDateTime createdAtUtc;

    public String getId() {
        return id;
    }

    public ServiceOrderCreationResponseBody setId(String id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ServiceOrderCreationResponseBody setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ServiceOrderCreationResponseBody setDescription(String description) {
        this.description = description;
        return this;
    }

    public OffsetDateTime getCreatedAtUtc() {
        return createdAtUtc;
    }

    public ServiceOrderCreationResponseBody setCreatedAtUtc(OffsetDateTime createdAtUtc) {
        this.createdAtUtc = createdAtUtc;
        return this;
    }
}
