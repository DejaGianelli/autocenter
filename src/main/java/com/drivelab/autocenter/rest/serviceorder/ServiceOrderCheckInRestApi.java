package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.rest.ProblemDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

public interface ServiceOrderCheckInRestApi extends ServiceOrderRestApi {

    @Operation(
            summary = "Check In a Service Order",
            description = "The check in is the creation of a new Service Order and returns the created Service Order."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Service Order checked in successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ServiceOrderCheckInResponseBody.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetails.class)
                    )
            )
    })
    @PostMapping
    ResponseEntity<ServiceOrderCheckInResponseBody> response(@Valid @RequestBody ServiceOrderCheckInRequestBody requestBody,
                                                             UriComponentsBuilder uriComponentsBuilder);
}
