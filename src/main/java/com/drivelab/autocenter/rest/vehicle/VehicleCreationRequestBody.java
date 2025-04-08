package com.drivelab.autocenter.rest.vehicle;

import com.drivelab.autocenter.rest.ValidInternalId;
import com.drivelab.autocenter.rest.ValidPublicId;
import io.swagger.v3.oas.annotations.media.Schema;

public class VehicleCreationRequestBody {

    @ValidPlate
    @Schema(description = "The vehicle's plate number", example = "ABC1234")
    private String plate;

    @ValidInternalId
    @Schema(description = "The vehicle's model id", example = "1")
    private Long modelId;

    @Schema(description = "The customer's id", example = "01JR70WP0023SKHR1YY2YWH27R")
    @ValidPublicId
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public String getPlate() {
        return plate;
    }

    public Long getModelId() {
        return modelId;
    }
}
