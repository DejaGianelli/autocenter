package com.drivelab.autocenter.domain.customer;

import com.drivelab.autocenter.domain.Cpf;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@AttributeOverrides({
        @AttributeOverride(name = "cpf.value", column = @Column(name = "cpf", nullable = false))
})
@Entity
@DiscriminatorValue(CustomerType.NATURAL_KEY_VALUE)
public class CustomerNatural extends Customer {

    @Embedded
    private Cpf cpf;

    protected CustomerNatural() {
    }

    public CustomerNatural(@NonNull Cpf cpf) {
        super(new CustomerPublicId(), CustomerType.NATURAL);
        this.cpf = cpf;
    }

    @Override
    public String document() {
        return cpf.toString();
    }
}
