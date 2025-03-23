package com.drivelab.autocenter.rest.product;

import com.drivelab.autocenter.domain.product.ProductCreationCommand;
import com.drivelab.autocenter.domain.product.ProductName;
import com.drivelab.autocenter.domain.product.Sku;
import org.springframework.stereotype.Component;

@Component
public class ProductCreationRequestBodyMapping {

    public ProductCreationCommand command(ProductCreationRequestBody requestBody) {
        return ProductCreationCommand.Builder
                .builder()
                .sku(new Sku(requestBody.getSku()))
                .name(new ProductName(requestBody.getName()))
                .build();
    }
}
