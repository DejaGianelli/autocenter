package com.drivelab.autocenter.rest.vehicle;

import com.drivelab.autocenter.domain.EntityNotFoundException;
import com.drivelab.autocenter.domain.customer.CustomerNotFoundException;
import com.drivelab.autocenter.domain.vehicle.Vehicle;
import com.drivelab.autocenter.domain.vehicle.VehicleCreationCommand;
import com.drivelab.autocenter.domain.vehicle.VehicleCreationUseCase;
import com.drivelab.autocenter.domain.vehicle.VehicleModelNotFoundException;
import com.drivelab.autocenter.rest.ProblemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleCreationRestController implements VehicleCreationRestApi {

    private final VehicleCreationUseCase useCase;
    private final VehicleCreationRequestBodyMapping requestMapping;
    private final VehicleCreationResponseMapping responseMapping;

    @Autowired
    public VehicleCreationRestController(VehicleCreationUseCase useCase,
                                         VehicleCreationRequestBodyMapping requestMapping,
                                         VehicleCreationResponseMapping responseMapping) {
        this.useCase = useCase;
        this.requestMapping = requestMapping;
        this.responseMapping = responseMapping;
    }

    @Override
    public ResponseEntity<VehicleCreationResponseBody> response(VehicleCreationRequestBody requestBody,
                                                                UriComponentsBuilder uriComponentsBuilder) {
        VehicleCreationCommand command = requestMapping.command(requestBody);
        Vehicle vehicle = useCase.newVehicle(command);
        VehicleCreationResponseBody responseBody = responseMapping.responseBody(vehicle);
        URI uri = uriComponentsBuilder.path("/v1/vehicles/{id}").buildAndExpand(vehicle.internalId().toString()).toUri();
        return ResponseEntity.created(uri).body(responseBody);
    }

    @ExceptionHandler({VehicleModelNotFoundException.class, CustomerNotFoundException.class})
    public ResponseEntity<ProblemDetails> notFoundResponse(EntityNotFoundException ex) {
        ProblemDetails problemDetails = new ProblemDetails(ex.getMessage(), BAD_REQUEST);
        return ResponseEntity.status(BAD_REQUEST).body(problemDetails);
    }
}
