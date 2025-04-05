package com.drivelab.autocenter.rest.serviceorder;

public class ServiceOrderCheckInRequestBody {

    private String customerId;
    private String vehicleId;
    private String observations;
    private Integer odometer;

    public Integer getOdometer() {
        return odometer;
    }

    public ServiceOrderCheckInRequestBody setOdometer(Integer odometer) {
        this.odometer = odometer;
        return this;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public ServiceOrderCheckInRequestBody setVehicleId(String vehicleId) {
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
