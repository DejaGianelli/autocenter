package com.drivelab.autocenter.rest.vehicle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;


public interface VehicleCreationRestApi extends VehicleRestApi {

    @PostMapping
    @Operation(summary = "Add new vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid Input")
    })
    ResponseEntity<VehicleCreationResponseBody> response(@Valid @RequestBody VehicleCreationRequestBody requestBody,
                                                         UriComponentsBuilder uriComponentsBuilder);
}
