package com.drivelab.autocenter.domain.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

@Embeddable
public class VehicleModelName {

    @Column(name = "vehicle_model_name", nullable = false)
    private String value;

    protected VehicleModelName() {
        //Empty constructor needed by ORM
    }

    public VehicleModelName(@NonNull String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
