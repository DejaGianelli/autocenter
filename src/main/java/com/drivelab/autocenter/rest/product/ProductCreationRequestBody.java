package com.drivelab.autocenter.rest.product;

public class ProductCreationRequestBody {
    private String sku;
    private String name;

    public ProductCreationRequestBody(String sku, String name) {
        this.sku = sku;
        this.name = name;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }
}
