package com.drivelab.autocenter.rest.vehicle;

import com.drivelab.autocenter.domain.vehicle.Vehicle;
import com.drivelab.autocenter.domain.vehicle.VehicleDetailsUseCase;
import com.drivelab.autocenter.domain.vehicle.VehiclePublicId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleDetailsRestController {

    private final VehicleDetailsUseCase useCase;
    private final VehicleDetailsResponseMapping responseBodyMapping;

    @Autowired
    public VehicleDetailsRestController(VehicleDetailsUseCase useCase,
                                        VehicleDetailsResponseMapping responseBodyMapping) {
        this.useCase = useCase;
        this.responseBodyMapping = responseBodyMapping;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDetailsResponseBody> detailsResponse(@PathVariable String id) {
        Vehicle product = useCase.vehicle(new VehiclePublicId(id));
        VehicleDetailsResponseBody responseBody = responseBodyMapping.responseBody(product);
        return ResponseEntity.ok(responseBody);
    }
}
