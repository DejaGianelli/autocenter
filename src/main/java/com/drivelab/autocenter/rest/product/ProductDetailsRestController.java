package com.drivelab.autocenter.rest.product;

import com.drivelab.autocenter.domain.product.Product;
import com.drivelab.autocenter.domain.product.ProductDetailsUseCase;
import com.drivelab.autocenter.domain.product.ProductPublicId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductDetailsRestController {

    private final ProductDetailsUseCase useCase;
    private final ProductDetailsResponseMapping responseBodyMapping;

    @Autowired
    public ProductDetailsRestController(ProductDetailsUseCase useCase,
                                        ProductDetailsResponseMapping responseBodyMapping) {
        this.useCase = useCase;
        this.responseBodyMapping = responseBodyMapping;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsResponseBody> detailsResponse(@PathVariable String id) {
        Product product = useCase.product(new ProductPublicId(id));
        ProductDetailsResponseBody responseBody = responseBodyMapping.responseBody(product);
        return ResponseEntity.ok(responseBody);
    }
}
