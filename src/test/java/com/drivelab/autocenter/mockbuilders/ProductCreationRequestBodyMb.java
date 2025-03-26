package com.drivelab.autocenter.mockbuilders;

import com.drivelab.autocenter.rest.product.ProductCreationRequestBody;

public class ProductCreationRequestBodyMb {

    private String sku;
    private String name;

    public ProductCreationRequestBodyMb() {
    }

    public ProductCreationRequestBody defaultRequest() {
        setSku("AGK94585-Yellow").setName("Produto Teste");
        return new ProductCreationRequestBody(sku, name);
    }

    public String getName() {
        return name;
    }

    public ProductCreationRequestBodyMb setName(String name) {
        this.name = name;
        return this;
    }

    public String getSku() {
        return sku;
    }

    public ProductCreationRequestBodyMb setSku(String sku) {
        this.sku = sku;
        return this;
    }
}
