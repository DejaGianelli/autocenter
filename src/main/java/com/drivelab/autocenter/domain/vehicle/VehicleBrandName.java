package com.drivelab.autocenter.domain.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

@Embeddable
public class VehicleBrandName {

    @Column(name = "vehicle_brand_name", nullable = false)
    private String value;

    protected VehicleBrandName() {
        //Empty constructor needed by ORM
    }

    public VehicleBrandName(@NonNull String value) {
        this.value = value;
    }

    public String name() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
