package com.drivelab.autocenter.rest;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Positive
@Digits(integer = 9, fraction = 2)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMoney {

    String message() default "ValidMoney";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
