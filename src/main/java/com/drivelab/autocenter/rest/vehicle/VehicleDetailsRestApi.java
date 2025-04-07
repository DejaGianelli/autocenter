package com.drivelab.autocenter.rest.vehicle;

import com.drivelab.autocenter.rest.ProblemDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface VehicleDetailsRestApi extends VehicleRestApi {

    @GetMapping("/{id}")
    @Operation(summary = "Get a vehicle by its id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetails.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Vehicle not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetails.class)
                    )
            )
    })
    ResponseEntity<VehicleDetailsResponseBody> response(@Parameter(description = "Vehicle id (ulid)", required = true)
                                                        @PathVariable String id);
}
