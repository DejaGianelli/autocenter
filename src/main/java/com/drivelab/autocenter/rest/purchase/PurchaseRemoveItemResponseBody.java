package com.drivelab.autocenter.rest.purchase;

import java.util.List;

public class PurchaseRemoveItemResponseBody {

    private final String id;
    private final double total;
    private final Supplier supplier;
    private final List<Item> items;

    public String getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public List<Item> getItems() {
        return items;
    }

    public PurchaseRemoveItemResponseBody(String id, double total, Supplier supplier, List<Item> items) {
        this.id = id;
        this.total = total;
        this.supplier = supplier;
        this.items = items;
    }

    public static class Item {
        private final Integer quantity;
        private final String productId;
        private final double unitCost;

        public Item(Integer quantity, String productId, double unitCost) {
            this.quantity = quantity;
            this.productId = productId;
            this.unitCost = unitCost;
        }

        public double getUnitCost() {
            return unitCost;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public String getProductId() {
            return productId;
        }
    }

    public static class Supplier {
        private final String id;
        private final String name;

        public Supplier(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
