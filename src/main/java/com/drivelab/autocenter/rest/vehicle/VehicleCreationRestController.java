package com.drivelab.autocenter.rest.vehicle;

import com.drivelab.autocenter.domain.DomainException;
import com.drivelab.autocenter.domain.vehicle.Vehicle;
import com.drivelab.autocenter.domain.vehicle.VehicleCreationCommand;
import com.drivelab.autocenter.domain.vehicle.VehicleCreationUseCase;
import com.drivelab.autocenter.domain.vehicle.VehicleModelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleCreationRestController {

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

    @PostMapping
    public ResponseEntity<VehicleCreationResponseBody> createdResponse(@RequestBody VehicleCreationRequestBody requestBody,
                                                                       UriComponentsBuilder uriComponentsBuilder) {
        try {
            VehicleCreationCommand command = requestMapping.command(requestBody);
            Vehicle vehicle = useCase.newVehicle(command);
            VehicleCreationResponseBody responseBody = responseMapping.responseBody(vehicle);
            URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(vehicle.internalId().toString()).toUri();
            return ResponseEntity.created(uri).body(responseBody);
        } catch (VehicleModelNotFoundException ex) {
            throw new DomainException(ex);
        }
    }
}
