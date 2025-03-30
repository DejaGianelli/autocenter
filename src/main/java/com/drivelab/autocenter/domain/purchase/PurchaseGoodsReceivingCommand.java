package com.drivelab.autocenter.domain.purchase;

import com.drivelab.autocenter.domain.product.ProductPublicId;
import org.springframework.lang.NonNull;

import java.util.List;

public class PurchaseGoodsReceivingCommand {

    private final PurchasePublicId purchaseId;
    private final List<Item> items;

    public PurchaseGoodsReceivingCommand(@NonNull PurchasePublicId id, @NonNull List<Item> items) {
        this.purchaseId = id;
        this.items = items;
    }

    @NonNull
    public PurchasePublicId purchaseId() {
        return purchaseId;
    }

    public List<Item> items() {
        return items;
    }

    public static class Item {
        private final ProductPublicId productId;
        private final Integer quantity;

        public Item(@NonNull ProductPublicId productId, @NonNull Integer quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        public ProductPublicId productId() {
            return productId;
        }

        public Integer quantity() {
            return quantity;
        }
    }
}
