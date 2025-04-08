package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.domain.EntityNotFoundException;
import com.drivelab.autocenter.domain.customer.CustomerNotFoundException;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrder;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderCheckInCommand;
import com.drivelab.autocenter.domain.serviceorder.ServiceOrderCheckInUseCase;
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
public class ServiceOrderCheckInRestController {

    private final ServiceOrderCheckInUseCase useCase;
    private final ServiceOrderCheckInRequestBodyMapping requestMapping;
    private final ServiceOrderCheckInResponseMapping responseMapping;

    @Autowired
    public ServiceOrderCheckInRestController(ServiceOrderCheckInUseCase useCase,
                                             ServiceOrderCheckInRequestBodyMapping requestMapping,
                                             ServiceOrderCheckInResponseMapping responseMapping) {
        this.useCase = useCase;
        this.requestMapping = requestMapping;
        this.responseMapping = responseMapping;
    }

    @PostMapping
    public ResponseEntity<ServiceOrderCheckInResponseBody> response(@RequestBody ServiceOrderCheckInRequestBody requestBody,
                                                                    UriComponentsBuilder uriComponentsBuilder) {
        ServiceOrderCheckInCommand command = requestMapping.command(requestBody);
        ServiceOrder serviceOrder = useCase.checkedIn(command);
        ServiceOrderCheckInResponseBody responseBody = responseMapping.responseBody(serviceOrder);
        URI uri = uriComponentsBuilder.path("/v1/service-orders/{id}").buildAndExpand(serviceOrder.publicId().toString()).toUri();
        return ResponseEntity.created(uri).body(responseBody);
    }

    @ExceptionHandler({CustomerNotFoundException.class, VehicleNotFoundException.class})
    public ResponseEntity<ProblemDetails> notFoundResponse(EntityNotFoundException ex) {
        ProblemDetails problemDetails = new ProblemDetails(ex.getMessage(), BAD_REQUEST);
        return ResponseEntity.status(BAD_REQUEST).body(problemDetails);
    }
}
