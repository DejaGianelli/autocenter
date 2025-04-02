package com.drivelab.autocenter.domain.serviceorder;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ServiceOrderServiceId implements Serializable {

    @Column(name = "service_order_id")
    private Long serviceOrderId;

    @Column(name = "service_id")
    private Long serviceId;

    protected ServiceOrderServiceId() {
    }

    public ServiceOrderServiceId(@NonNull Long serviceId) {
        this.serviceId = serviceId;
    }

    public void setServiceOrderId(@Nullable Long serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public void setServiceId(@Nullable Long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOrderServiceId that = (ServiceOrderServiceId) o;
        return Objects.equals(serviceOrderId, that.serviceOrderId) && Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceOrderId, serviceId);
    }
}
