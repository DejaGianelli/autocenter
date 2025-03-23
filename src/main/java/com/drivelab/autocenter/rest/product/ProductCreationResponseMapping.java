package com.drivelab.autocenter.rest.product;

import com.drivelab.autocenter.domain.product.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductCreationResponseMapping {

    public ProductCreationResponseBody responseBody(Product product) {
        return new ProductCreationResponseBody(product.sku().toString(), product.name().toString());
    }
}
