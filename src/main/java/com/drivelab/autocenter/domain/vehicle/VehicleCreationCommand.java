package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.InternalId;

public class VehicleCreationCommand {

    private final Plate plate;
    private final InternalId modelId;

    public VehicleCreationCommand(Plate plate, InternalId modelId) {
        this.plate = plate;
        this.modelId = modelId;
    }

    public Plate plate() {
        return plate;
    }

    public InternalId modelId() {
        return modelId;
    }
}
