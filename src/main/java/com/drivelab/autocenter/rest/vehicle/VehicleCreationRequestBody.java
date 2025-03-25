package com.drivelab.autocenter.rest.vehicle;

public class VehicleCreationRequestBody {
    private String plate;
    private Long modelId;
    private String customerId;
    private Integer odometer;

    public String getCustomerId() {
        return customerId;
    }

    public String getPlate() {
        return plate;
    }

    public Long getModelId() {
        return modelId;
    }

    public Integer getOdometer() {
        return odometer;
    }
}
