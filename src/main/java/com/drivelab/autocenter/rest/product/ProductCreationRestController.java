package com.drivelab.autocenter.rest.product;

import com.drivelab.autocenter.domain.product.Product;
import com.drivelab.autocenter.domain.product.ProductCreationCommand;
import com.drivelab.autocenter.domain.product.ProductCreationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/products")
public class ProductCreationRestController {

    private final ProductCreationUseCase useCase;
    private final ProductCreationRequestBodyMapping requestMapping;
    private final ProductCreationResponseMapping responseMapping;

    @Autowired
    public ProductCreationRestController(ProductCreationUseCase useCase,
                                         ProductCreationRequestBodyMapping requestMapping,
                                         ProductCreationResponseMapping responseMapping) {
        this.useCase = useCase;
        this.requestMapping = requestMapping;
        this.responseMapping = responseMapping;
    }

    @PostMapping
    public ResponseEntity<ProductCreationResponseBody> createdResponse(@RequestBody ProductCreationRequestBody requestBody,
                                                                       UriComponentsBuilder uriComponentsBuilder) {
        ProductCreationCommand command = requestMapping.command(requestBody);
        Product product = useCase.newProduct(command);
        ProductCreationResponseBody responseBody = responseMapping.responseBody(product);
        URI uri = uriComponentsBuilder.path("/{sku}").buildAndExpand(product.sku().toString()).toUri();
        return ResponseEntity.created(uri).body(responseBody);
    }
}
