package com.drivelab.autocenter.rest.vehicle;

import com.drivelab.autocenter.domain.vehicle.Plate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class VehicleCreationRequestBody {

    @ValidPlate
    @Schema(description = "The vehicle's plate number", example = "ABC1234")
    private String plate;

    @NotNull(message = "model-id.invalid")
    @Positive(message = "model-id.invalid")
    @Schema(description = "The vehicle's model id", example = "1")
    private Long modelId;

    @Schema(description = "The customer's id", example = "01JR70WP0023SKHR1YY2YWH27R")
    @NotBlank(message = "customer-id.invalid")
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

    @Constraint(validatedBy = {})
    @ReportAsSingleViolation
    @NotBlank
    @Pattern(regexp = Plate.PATTERN)
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ValidPlate {
        String message() default "plate.invalid";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }
}
