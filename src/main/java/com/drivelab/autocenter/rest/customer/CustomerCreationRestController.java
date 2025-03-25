package com.drivelab.autocenter.rest.customer;

import com.drivelab.autocenter.domain.customer.Customer;
import com.drivelab.autocenter.domain.customer.CustomerCreationCommand;
import com.drivelab.autocenter.domain.customer.CustomerCreationUseCase;
import com.drivelab.autocenter.domain.customer.CustomerType;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.EnumMap;
import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerCreationRestController {

    private final List<CustomerCreationUseCase> useCaseList;
    private final CustomerCreationRequestBodyMapping requestMapping;
    private final CustomerCreationResponseMapping responseMapping;
    private EnumMap<CustomerType, CustomerCreationUseCase> useCaseMap;

    @Autowired
    public CustomerCreationRestController(List<CustomerCreationUseCase> useCaseList,
                                          CustomerCreationRequestBodyMapping requestMapping,
                                          CustomerCreationResponseMapping responseMapping) {
        this.useCaseList = useCaseList;
        this.requestMapping = requestMapping;
        this.responseMapping = responseMapping;
    }

    @PostMapping
    public ResponseEntity<CustomerCreationResponseBody> createdResponse(@RequestBody CustomerCreationRequestBody requestBody,
                                                                        UriComponentsBuilder uriComponentsBuilder) {
        CustomerCreationCommand command = requestMapping.command(requestBody);
        Customer customer = useCaseMap.get(command.type()).newCustomer(command);
        CustomerCreationResponseBody responseBody = responseMapping.responseBody(customer);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(customer.publicId().toString()).toUri();
        return ResponseEntity.created(uri).body(responseBody);
    }

    @PostConstruct
    public void init() {
        this.useCaseMap = new EnumMap<>(CustomerType.class);
        for (CustomerCreationUseCase useCase : this.useCaseList) {
            useCaseMap.put(useCase.type(), useCase);
        }
    }
}
