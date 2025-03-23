package com.drivelab.autocenter.domain.product;

public class ExistentProductException extends RuntimeException {
    public ExistentProductException(Sku sku) {
        super("Product of " + sku + " already exists");
    }
}
