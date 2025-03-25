package com.drivelab.autocenter.domain.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

@Embeddable
public class Odometer {

    @Column(name = "odometer_value")
    private Integer value;

    protected Odometer() {
    }

    public Odometer(@NonNull Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
