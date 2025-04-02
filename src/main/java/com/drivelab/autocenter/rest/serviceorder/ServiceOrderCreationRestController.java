package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.domain.EntityNotFoundException;
import com.drivelab.autocenter.domain.customer.CustomerNotFoundException;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrder;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderCreationCommand;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderCreationUseCase;
import com.drivelab.autocenter.domain.vehicle.VehicleNotFoundException;
import com.drivelab.autocenter.rest.ProblemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/v1/service-orders")
public class ServiceOrderCreationRestController {

    private final ServiceOrderCreationUseCase useCase;
    private final ServiceOrderCreationRequestBodyMapping requestMapping;
    private final ServiceOrderCreationResponseMapping responseMapping;

    @Autowired
    public ServiceOrderCreationRestController(ServiceOrderCreationUseCase useCase,
                                              ServiceOrderCreationRequestBodyMapping requestMapping,
                                              ServiceOrderCreationResponseMapping responseMapping) {
        this.useCase = useCase;
        this.requestMapping = requestMapping;
        this.responseMapping = responseMapping;
    }

    @PostMapping
    public ResponseEntity<ServiceOrderCreationResponseBody> response(@RequestBody ServiceOrderCreationRequestBody requestBody,
                                                                     UriComponentsBuilder uriComponentsBuilder) {
        ServiceOrderCreationCommand command = requestMapping.command(requestBody);
        ServiceOrder serviceOrder = useCase.newServiceOrder(command);
        ServiceOrderCreationResponseBody responseBody = responseMapping.responseBody(serviceOrder);
        URI uri = uriComponentsBuilder.path("/v1/service-orders/{id}").buildAndExpand(serviceOrder.publicId().toString()).toUri();
        return ResponseEntity.created(uri).body(responseBody);
    }

    @ExceptionHandler({CustomerNotFoundException.class, VehicleNotFoundException.class})
    public ResponseEntity<ProblemDetails> notFoundResponse(EntityNotFoundException ex) {
        ProblemDetails problemDetails = new ProblemDetails(ex.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(problemDetails);
    }
}
