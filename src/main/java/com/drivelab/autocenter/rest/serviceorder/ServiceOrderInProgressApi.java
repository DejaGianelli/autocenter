package com.drivelab.autocenter.rest.serviceorder;

import com.drivelab.autocenter.rest.ProblemDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface ServiceOrderInProgressApi extends ServiceOrderRestApi {

    @Operation(
            summary = "Mark a Service Order as In Progress",
            description = "Mark a Service Order as In Progress.",
            tags = {"In Progress"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Service Order marked as in progress successfully",
                    content = @Content(mediaType = "application/json")
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
    ResponseEntity<Void> response(@PathVariable String serviceOrderId);
}
