package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.DomainEntity;
import com.drivelab.autocenter.domain.DomainException;
import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.MoneyAttributeConverter;
import com.drivelab.autocenter.domain.product.Product;
import com.drivelab.autocenter.domain.product.ProductPublicId;
import com.drivelab.autocenter.domain.supplier.Supplier;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.isNull;

@Entity
@Table(name = "purchases")
public class Purchase extends DomainEntity {

    @Embedded
    private PurchasePublicId publicId;

    @Convert(converter = PurchaseStatusAttributeConverter.class)
    private PurchaseStatus status;

    @Column(name = "billed_at_utc")
    private OffsetDateTime billedAtUtc;

    @Column(name = "received_at_utc")
    private OffsetDateTime receivedAtUtc;

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
        this();
        this.publicId = new PurchasePublicId();
        this.status = PurchaseStatus.CREATED;
        this.total = Money.ZERO;
        this.supplier = supplier;
    }

    public boolean allProductsReceived() {
        boolean received = true;
        for (PurchaseItem item : items) {
            if (!item.allReceived()) {
                return false;
            }
        }
        return received;
    }

    public boolean hasItems() {
        return !this.items.isEmpty();
    }

    public void markAsBilled() {
        updateStatus(PurchaseStatus.BILLED);
        if (!isNull(this.billedAtUtc)) {
            throw new DomainException("Purchase of id " + this.publicId + " was already billed");
        }
        this.billedAtUtc = Instant.now().atOffset(ZoneOffset.UTC);
    }

    public void markAsReceived() {
        updateStatus(PurchaseStatus.RECEIVED);
        if (!isNull(this.receivedAtUtc)) {
            throw new DomainException("Purchase of id " + this.publicId + " was already received");
        }
        this.receivedAtUtc = Instant.now().atOffset(ZoneOffset.UTC);
    }

    public Optional<PurchaseItem> item(@NonNull ProductPublicId id) {
        return items.stream()
                .filter(i -> i.product().publicId().equals(id))
                .findFirst();
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

    public void removeItem(Product product) {
        for (PurchaseItem item : items) {
            if (item.product().equals(product)) {
                items.remove(item);
                updateTotal();
                return;
            }
        }
    }

    public Money totalItemsReceivedCost() {
        int total = 0;
        for (PurchaseItem item : items) {
            total += item.totalReceived().cents();
        }
        return Money.create(total);
    }

    public Money totalItemsCost() {
        int total = 0;
        for (PurchaseItem item : items) {
            total += item.total().cents();
        }
        return Money.create(total);
    }

    private void updateStatus(@NonNull PurchaseStatus newStatus) {
        if (!this.status.canChangeTo(newStatus)) {
            throw new PurchaseInvalidStatusChangeException(this.status, newStatus);
        }
        this.status = newStatus;
    }

    private void updateTotal() {
        this.total = totalItemsCost();
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

    public @Nullable OffsetDateTime billedAtUtc() {
        return billedAtUtc;
    }

    public @Nullable OffsetDateTime receivedAtUtc() {
        return receivedAtUtc;
    }
}
