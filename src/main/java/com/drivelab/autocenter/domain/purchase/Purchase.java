package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.DomainEntity;
import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.MoneyAttributeConverter;
import com.drivelab.autocenter.domain.supplier.Supplier;
import com.drivelab.autocenter.domain.supplier.SupplierPublicId;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "purchases")
public class Purchase extends DomainEntity {

    @Embedded
    private SupplierPublicId publicId;

    @Convert(converter = PurchaseStatusAttributeConverter.class)
    private PurchaseStatus status;

    @Convert(converter = MoneyAttributeConverter.class)
    private Money total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "supplier_id")
    private Supplier supplier;

    protected Purchase() {
    }

    public Purchase(@NonNull Supplier supplier) {
        this.publicId = new SupplierPublicId();
        this.status = PurchaseStatus.CREATED;
        this.total = Money.ZERO;
        this.supplier = supplier;
    }

    public SupplierPublicId publicId() {
        return publicId;
    }

    public PurchaseStatus status() {
        return status;
    }

    public Money total() {
        return total;
    }

    public Supplier supplier() {
        return supplier;
    }
}
