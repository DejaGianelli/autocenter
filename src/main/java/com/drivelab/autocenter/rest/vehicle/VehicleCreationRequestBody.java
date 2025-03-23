package com.drivelab.autocenter.rest.vehicle;

public class VehicleCreationRequestBody {
    private String plate;
    private Long modelId;

    public String getPlate() {
        return plate;
    }

    public Long getModelId() {
        return modelId;
    }
}
