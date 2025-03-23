package com.drivelab.autocenter.rest.product;

public class ProductCreationResponseBody {
    private final String sku;
    private final String name;

    public ProductCreationResponseBody(String sku, String name) {
        this.sku = sku;
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }
}
