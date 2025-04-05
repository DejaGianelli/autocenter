package com.drivelab.autocenter.rest.serviceorder;

import org.springframework.lang.NonNull;

import java.util.List;

public class ServiceOrderPutServiceResponseBody {

    private final String id;
    private final double total;
    private final List<Service> services;

    public String getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public List<Service> getServices() {
        return services;
    }

    public ServiceOrderPutServiceResponseBody(String id, double total, List<Service> services) {
        this.id = id;
        this.total = total;
        this.services = services;
    }

    public static class Service {

        private final Double price;
        private final String id;

        public Service(@NonNull Double price, @NonNull String id) {
            this.price = price;
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public Double getPrice() {
            return price;
        }
    }
}
