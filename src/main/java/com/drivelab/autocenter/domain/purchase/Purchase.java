package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.DomainEntity;
import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.MoneyAttributeConverter;
import com.drivelab.autocenter.domain.supplier.Supplier;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "purchases")
public class Purchase extends DomainEntity {

    @Embedded
    private PurchasePublicId publicId;

    @Convert(converter = PurchaseStatusAttributeConverter.class)
    private PurchaseStatus status;

    @Convert(converter = MoneyAttributeConverter.class)
    private Money total;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "supplier_id")
    private Supplier supplier;

    @OneToMany(
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "purchase",
            cascade = CascadeType.ALL
    )
    private final Set<PurchaseItem> items;

    protected Purchase() {
        this.items = new HashSet<>();
    }

    public Purchase(@NonNull Supplier supplier) {
        this.publicId = new PurchasePublicId();
        this.status = PurchaseStatus.CREATED;
        this.total = Money.ZERO;
        this.supplier = supplier;
        this.items = new HashSet<>();
    }

    public void addItem(@NonNull PurchaseItem item) {
        item.setPurchase(this);
        for (PurchaseItem pi : items) {
            if (pi.equals(item)) {
                pi.update(item);
                updateTotal();
                return;
            }
        }
        items.add(item);
        updateTotal();
    }

    private void updateTotal() {
        int total = 0;
        for (PurchaseItem item : items) {
            total += item.total().cents();
        }
        this.total = Money.create(total);
    }

    public PurchasePublicId publicId() {
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

    public Set<PurchaseItem> items() {
        return items;
    }
}
