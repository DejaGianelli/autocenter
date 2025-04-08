package com.drivelab.autocenter.rest;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {})
@ReportAsSingleViolation
@NotNull
@Positive
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidInternalId {

    String message() default "ValidInternalId";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
