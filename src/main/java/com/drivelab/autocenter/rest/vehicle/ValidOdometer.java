package com.drivelab.autocenter.rest.vehicle;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Positive
@Min(0)
@Max(Integer.MAX_VALUE)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOdometer {

    String message() default "ValidOdometer";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
