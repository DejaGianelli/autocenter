package com.drivelab.autocenter.domain.inventory;

import com.drivelab.autocenter.domain.DomainEntity;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "inventory_movements")
public class InventoryMovement extends DomainEntity {

    @Embedded
    private InventoryQuantity quantity;

    @Convert(converter = InventoryMovementTypeConverter.class)
    private InventoryMovementType type;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    protected InventoryMovement() {
        //Public empty constructor needed by ORM
    }

    public InventoryMovement(@NonNull InventoryMovementType type,
                             @NonNull InventoryQuantity quantity,
                             @NonNull Inventory inventory) {
        this.quantity = quantity;
        this.type = type;
        this.inventory = inventory;
    }

    public InventoryQuantity quantity() {
        return quantity;
    }

    public InventoryMovementType type() {
        return type;
    }

    public Inventory inventory() {
        return inventory;
    }
}
