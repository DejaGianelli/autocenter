package com.drivelab.autocenter.rest;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {})
@ReportAsSingleViolation
@NotBlank
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPublicId {

    String message() default "ValidPublicId";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
