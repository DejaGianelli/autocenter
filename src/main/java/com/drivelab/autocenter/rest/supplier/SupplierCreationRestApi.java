package com.drivelab.autocenter.rest.supplier;

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

public interface SupplierCreationRestApi extends SupplierRestApi {

    @Operation(
            summary = "Create a new supplier",
            description = "This endpoint creates a new supplier and returns the created supplier details."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Supplier created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SupplierCreationResponseBody.class)
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
    ResponseEntity<SupplierCreationResponseBody> response(@Valid @RequestBody SupplierCreationRequestBody requestBody,
                                                          UriComponentsBuilder uriComponentsBuilder);
}
