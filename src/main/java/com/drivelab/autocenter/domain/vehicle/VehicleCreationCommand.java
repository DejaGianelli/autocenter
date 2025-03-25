package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.InternalId;
import com.drivelab.autocenter.domain.customer.CustomerPublicId;

public class VehicleCreationCommand {

    private final Plate plate;
    private final InternalId modelId;
    private final CustomerPublicId customerId;

    public VehicleCreationCommand(Plate plate, InternalId modelId, CustomerPublicId customerId) {
        this.plate = plate;
        this.modelId = modelId;
        this.customerId = customerId;
    }

    public Plate plate() {
        return plate;
    }

    public InternalId modelId() {
        return modelId;
    }

    public CustomerPublicId customerId() {
        return customerId;
    }
}
