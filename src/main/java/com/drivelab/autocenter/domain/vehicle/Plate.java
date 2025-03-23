package com.drivelab.autocenter.domain.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

@Embeddable
public class Plate {

    @Column(name = "plate")
    private String value;

    protected Plate() {
        //Public empty constructor needed by ORM
    }

    public Plate(@NonNull String value) {
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
