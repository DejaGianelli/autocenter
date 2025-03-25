package com.drivelab.autocenter.rest.vehicle;

public class VehicleDetailsResponseBody {

    private String id;
    private String plate;
    private Model model;
    private Customer customer;
    private Integer odometer;

    private VehicleDetailsResponseBody(Builder builder) {
        id = builder.id;
        plate = builder.plate;
        model = builder.model;
        customer = builder.customer;
        odometer = builder.odometer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Integer getOdometer() {
        return odometer;
    }

    public Model getModel() {
        return model;
    }

    public String getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public static class Model {

        private final String name;

        public Model(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Customer {

        private final String document;

        public Customer(String document) {
            this.document = document;
        }

        public String getDocument() {
            return document;
        }
    }

    public static final class Builder {
        private String id;
        private String plate;
        private Model model;
        private Customer customer;
        private Integer odometer;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder plate(String val) {
            plate = val;
            return this;
        }

        public Builder model(Model val) {
            model = val;
            return this;
        }

        public Builder customer(Customer val) {
            customer = val;
            return this;
        }

        public Builder odometer(Integer val) {
            odometer = val;
            return this;
        }

        public VehicleDetailsResponseBody build() {
            return new VehicleDetailsResponseBody(this);
        }
    }
}
