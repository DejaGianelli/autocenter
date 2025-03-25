package com.drivelab.autocenter.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

@Embeddable
public class Cnpj {

    @Column(name = "cnpj")
    private String value;

    protected Cnpj() {
    }

    public Cnpj(@NonNull String value) {
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
