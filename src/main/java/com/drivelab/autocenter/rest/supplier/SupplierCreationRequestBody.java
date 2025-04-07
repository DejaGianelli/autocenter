package com.drivelab.autocenter.rest.supplier;

import com.drivelab.autocenter.domain.supplier.SupplierName;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.lang.NonNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class SupplierCreationRequestBody {

    @CNPJ(message = "supplier.cnpj.invalid")
    private String cnpj;

    @ValidSupplierName
    private String name;

    public SupplierCreationRequestBody() {
    }

    public SupplierCreationRequestBody(@NonNull String cnpj, @NonNull String name) {
        this.cnpj = cnpj;
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName() {
        return name;
    }

    @Constraint(validatedBy = {})
    @ReportAsSingleViolation
    @NotBlank
    @Length(min = SupplierName.MIN_SIZE, max = SupplierName.MAX_SIZE)
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ValidSupplierName {
        String message() default "supplier.name.invalid";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }
}
