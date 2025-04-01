package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.DomainException;
import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.MoneyAttributeConverter;
import com.drivelab.autocenter.domain.inventory.InventoryQuantity;
import com.drivelab.autocenter.domain.product.Product;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.NonNull;

import java.util.Objects;

@Entity
@Table(name = "purchase_items")
public class PurchaseItem {

    @EmbeddedId
    private final PurchaseItemId id;

    @MapsId("purchaseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @Fetch(FetchMode.JOIN)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Product product;

    private Integer quantity;

    private Integer receivedQuantity;

    @Convert(converter = MoneyAttributeConverter.class)
    private Money unitCost;

    protected PurchaseItem() {
        this.id = new PurchaseItemId();
        this.receivedQuantity = 0;
    }

    public PurchaseItem(@NonNull Product product, @NonNull Integer quantity, @NonNull Money unitCost) {
        this.product = product;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.receivedQuantity = 0;
        this.id = new PurchaseItemId(product.internalId().value());
    }

    public Money total() {
        return Money.create(unitCost.cents() * quantity);
    }

    public Money totalReceived() {
        return Money.create(unitCost.cents() * receivedQuantity);
    }

    public void update(@NonNull PurchaseItem item) {
        this.quantity = item.quantity;
        this.unitCost = item.unitCost;
    }

    public boolean allReceived() {
        return Objects.equals(this.quantity, this.receivedQuantity);
    }

    public void receive(@NonNull InventoryQuantity quantity) {
        int newQuantity = this.receivedQuantity + quantity.value();
        if (newQuantity > this.quantity) {
            throw new DomainException("Received quantity of product " + product.publicId() + " exceeded the purchase");
        }
        this.receivedQuantity = newQuantity;
    }

    public void setPurchase(@NonNull Purchase purchase) {
        this.purchase = purchase;
        this.id.setPurchaseId(purchase.internalId().value());
    }

    public Purchase purchase() {
        return purchase;
    }

    public Product product() {
        return product;
    }

    public Money unitCost() {
        return unitCost;
    }

    public Integer quantity() {
        return quantity;
    }

    public PurchaseItemId id() {
        return id;
    }

    public Integer receivedQuantity() {
        return receivedQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseItem that = (PurchaseItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

