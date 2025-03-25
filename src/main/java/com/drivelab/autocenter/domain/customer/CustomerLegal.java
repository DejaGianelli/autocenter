package com.drivelab.autocenter.domain.customer;

import com.drivelab.autocenter.domain.Cnpj;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@AttributeOverrides({
        @AttributeOverride(name = "cnpj.value", column = @Column(name = "cnpj", nullable = false))
})
@Entity
@DiscriminatorValue(CustomerType.LEGAL_KEY_VALUE)
public class CustomerLegal extends Customer {

    @Embedded
    private Cnpj cnpj;

    protected CustomerLegal() {
    }

    public CustomerLegal(@NonNull Cnpj cnpj) {
        super(new CustomerPublicId(), CustomerType.LEGAL);
        this.cnpj = cnpj;
    }

    @Override
    public String document() {
        return cnpj.toString();
    }
}
