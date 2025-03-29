package com.drivelab.autocenter.domain.purchase;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PurchaseItemId implements Serializable {

    @Column(name = "purchase_id")
    private Long purchaseId;

    @Column(name = "product_id")
    private Long productId;

    protected PurchaseItemId() {
    }

    public PurchaseItemId(@NonNull Long productId) {
        this.productId = productId;
    }

    public void setPurchaseId(@Nullable Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setProductId(@Nullable Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseItemId that = (PurchaseItemId) o;
        return Objects.equals(purchaseId, that.purchaseId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId, productId);
    }
}
