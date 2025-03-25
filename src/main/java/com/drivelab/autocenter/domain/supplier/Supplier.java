package com.drivelab.autocenter.domain.supplier;

import com.drivelab.autocenter.domain.Cnpj;
import com.drivelab.autocenter.domain.DomainEntity;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@AttributeOverrides({
        @AttributeOverride(name = "cnpj.value", column = @Column(name = "cnpj", nullable = false)),
        @AttributeOverride(name = "name.value", column = @Column(name = "name", nullable = false))
})
@Entity
@Table(name = "suppliers")
public class Supplier extends DomainEntity {

    @Embedded
    private SupplierPublicId publicId;

    @Embedded
    private Cnpj cnpj;

    @Embedded
    private SupplierName name;

    protected Supplier() {
    }

    public Supplier(@NonNull Cnpj cnpj, @NonNull SupplierName name) {
        this.publicId = new SupplierPublicId();
        this.cnpj = cnpj;
        this.name = name;
    }

    public SupplierPublicId publicId() {
        return publicId;
    }

    public Cnpj cnpj() {
        return cnpj;
    }

    public SupplierName name() {
        return name;
    }
}
