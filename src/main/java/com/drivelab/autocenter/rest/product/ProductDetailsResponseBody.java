package com.drivelab.autocenter.rest.product;

public class ProductDetailsResponseBody {
    private final String id;
    private final String sku;
    private final String name;

    public ProductDetailsResponseBody(String id, String sku, String name) {
        this.id = id;
        this.sku = sku;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }
}
