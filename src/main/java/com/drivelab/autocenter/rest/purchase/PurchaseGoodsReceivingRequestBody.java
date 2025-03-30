package com.drivelab.autocenter.rest.purchase;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PurchaseGoodsReceivingRequestBody {

    private final Set<Item> items;

    public PurchaseGoodsReceivingRequestBody() {
        this.items = new HashSet<>();
    }

    public Set<Item> getItems() {
        return items;
    }

    public static class Item {
        private String productId;
        private Integer quantity;

        public Item(String productId, Integer quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        public String getProductId() {
            return productId;
        }

        public Item setProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public Item setQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return Objects.equals(productId, item.productId);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(productId);
        }
    }
}
