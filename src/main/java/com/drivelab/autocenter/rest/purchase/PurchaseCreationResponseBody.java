package com.drivelab.autocenter.rest.purchase;

public class PurchaseCreationResponseBody {

    private final String id;
    private final double total;
    private final Supplier supplier;

    public PurchaseCreationResponseBody(String id, double total, Supplier supplier) {
        this.id = id;
        this.total = total;
        this.supplier = supplier;
    }

    public String getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public Supplier getSupplier() {
        return supplier;
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
