package com.drivelab.autocenter.rest.product;

import com.drivelab.autocenter.domain.product.ProductName;
import com.drivelab.autocenter.domain.product.ProductPublicId;
import com.drivelab.autocenter.domain.product.ProductUpdateCommand;
import org.springframework.stereotype.Component;

@Component
public class ProductUpdateRequestMapping {

    public ProductUpdateCommand command(String id, ProductUpdateRequestBody requestBody) {
        return ProductUpdateCommand.Builder
                .builder()
                .publicId(new ProductPublicId(id))
                .name(new ProductName(requestBody.getName()))
                .build();
    }
}
