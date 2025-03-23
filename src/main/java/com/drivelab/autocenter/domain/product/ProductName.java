package com.drivelab.autocenter.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductName {

    @Column(name = "product_name")
    private String value;

    protected ProductName() {
        //Public empty constructor needed by ORM
    }

    public ProductName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
