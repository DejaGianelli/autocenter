package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.rest.ProblemDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ServiceOrderPutServiceRestApi extends ServiceOrderRestApi {

    @Operation(
            summary = "Put a service into Service Order",
            description = "This endpoint puts a service into Service Order and returns the updated Service Order.",
            tags = {"Supplier Creation"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Service Order created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ServiceOrderPutServiceResponseBody.class)
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
    @PutMapping
    ResponseEntity<ServiceOrderPutServiceResponseBody> response(@PathVariable String serviceOrderId,
                                                                @PathVariable String serviceId,
                                                                @Valid @RequestBody ServiceOrderPutServiceRequestBody requestBody);
}
