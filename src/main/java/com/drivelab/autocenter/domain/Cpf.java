package com.drivelab.autocenter.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

@Embeddable
public class Cpf {

    @Column(name = "cpf")
    private String value;

    protected Cpf() {
    }

    public Cpf(@NonNull String value) {
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
