package com.drivelab.autocenter.rest.vehicle;

import org.springframework.lang.NonNull;

public class VehicleCreationResponseBody {
    private final String id;
    private final String plate;
    private final String model;

    public VehicleCreationResponseBody(@NonNull String id, @NonNull String plate, @NonNull String model) {
        this.id = id;
        this.plate = plate;
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public String getModel() {
        return model;
    }

    public String getId() {
        return id;
    }
}
