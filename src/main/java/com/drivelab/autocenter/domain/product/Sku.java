package com.drivelab.autocenter.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Sku {

    @Column(name = "product_sku")
    private String value;

    protected Sku() {
        //Public empty constructor needed by ORM
    }

    public Sku(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
