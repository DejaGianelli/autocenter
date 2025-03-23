package com.drivelab.autocenter.rest.vehicle;

public class VehicleCreationResponseBody {
    private final String plate;
    private final String model;

    public VehicleCreationResponseBody(String plate, String model) {
        this.plate = plate;
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public String getModel() {
        return model;
    }
}
