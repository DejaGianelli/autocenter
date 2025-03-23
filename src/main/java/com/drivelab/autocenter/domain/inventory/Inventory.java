package com.drivelab.autocenter.domain.inventory;

import com.drivelab.autocenter.domain.DomainEntity;
import com.drivelab.autocenter.domain.product.Product;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@AttributeOverrides({@AttributeOverride(name = "quantity.value", column = @Column(name = "quantity"))})
@Entity
@Table(name = "inventory")
public class Inventory extends DomainEntity {

    @Embedded
    private InventoryQuantity quantity;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    protected Inventory() {
        //Public empty constructor needed by ORM
    }

    public Inventory(Product product) {
        this.quantity = InventoryQuantity.ZERO;
        this.product = product;
    }

    public Inventory(InventoryQuantity quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public void update(@NonNull InventoryMovementType type, @NonNull InventoryQuantity quantity) {
        try {
            switch (type) {
                case ENTRY -> this.quantity = this.quantity.incrementedBy(quantity);
                case WITHDRAW -> this.quantity = this.quantity.decrementedBy(quantity);
            }
        } catch (IllegalStateException ex) {
            throw InsufficientProductQuantityException.Builder.builder()
                    .cause(ex)
                    .current(this.quantity)
                    .quantity(quantity)
                    .publicId(product.publicId())
                    .build();
        }
    }

    public InventoryQuantity quantity() {
        return quantity;
    }

    public Product product() {
        return product;
    }
}
