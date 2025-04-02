package com.drivelab.autocenter.rest.serviceorder;

public class ServiceOrderCreationRequestBody {

    private String customerId;
    private String vehicleId;
    private String observations;

    public String getVehicleId() {
        return vehicleId;
    }

    public ServiceOrderCreationRequestBody setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
