package com.drivelab.autocenter.rest.product;

import com.drivelab.autocenter.domain.product.Product;
import com.drivelab.autocenter.domain.product.ProductUpdateCommand;
import com.drivelab.autocenter.domain.product.ProductUpdateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
public class ProductUpdateRestController {

    private final ProductUpdateUseCase useCase;
    private final ProductUpdateResponseMapping responseBodyMapping;
    private final ProductUpdateRequestMapping requestMapping;

    @Autowired
    public ProductUpdateRestController(ProductUpdateUseCase useCase,
                                       ProductUpdateResponseMapping responseBodyMapping,
                                       ProductUpdateRequestMapping requestMapping) {
        this.useCase = useCase;
        this.responseBodyMapping = responseBodyMapping;
        this.requestMapping = requestMapping;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductUpdateResponseBody> updateResponse(@PathVariable String id,
                                                                    @RequestBody ProductUpdateRequestBody requestBody) {
        ProductUpdateCommand command = requestMapping.command(id, requestBody);
        Product product = useCase.updatedProduct(command);
        ProductUpdateResponseBody responseBody = responseBodyMapping.responseBody(product);
        return ResponseEntity.ok(responseBody);
    }
}
