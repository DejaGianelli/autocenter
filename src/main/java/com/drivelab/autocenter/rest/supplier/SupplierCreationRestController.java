package com.drivelab.autocenter.rest.supplier;

import com.drivelab.autocenter.domain.supplier.Supplier;
import com.drivelab.autocenter.domain.supplier.SupplierCreationCommand;
import com.drivelab.autocenter.domain.supplier.SupplierCreationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/suppliers")
public class SupplierCreationRestController {

    private final SupplierCreationUseCase useCase;
    private final SupplierCreationRequestBodyMapping requestMapping;
    private final SupplierCreationResponseMapping responseMapping;

    @Autowired
    public SupplierCreationRestController(SupplierCreationUseCase useCase,
                                          SupplierCreationRequestBodyMapping requestMapping,
                                          SupplierCreationResponseMapping responseMapping) {
        this.useCase = useCase;
        this.requestMapping = requestMapping;
        this.responseMapping = responseMapping;
    }

    @PostMapping
    public ResponseEntity<SupplierCreationResponseBody> createdResponse(@RequestBody SupplierCreationRequestBody requestBody,
                                                                        UriComponentsBuilder uriComponentsBuilder) {
        SupplierCreationCommand command = requestMapping.command(requestBody);
        Supplier supplier = useCase.newSupplier(command);
        SupplierCreationResponseBody responseBody = responseMapping.responseBody(supplier);
        URI uri = uriComponentsBuilder.path("/v1/suppliers/{id}").buildAndExpand(supplier.publicId().toString()).toUri();
        return ResponseEntity.created(uri).body(responseBody);
    }
}
