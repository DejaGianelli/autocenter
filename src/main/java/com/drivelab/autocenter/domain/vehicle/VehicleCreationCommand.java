package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.InternalId;
import com.drivelab.autocenter.domain.customer.CustomerPublicId;

public class VehicleCreationCommand {

    private Plate plate;
    private InternalId modelId;
    private CustomerPublicId customerId;

    private VehicleCreationCommand(Builder builder) {
        plate = builder.plate;
        modelId = builder.modelId;
        customerId = builder.customerId;
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

    public static final class Builder {
        private Plate plate;
        private InternalId modelId;
        private CustomerPublicId customerId;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder plate(Plate val) {
            plate = val;
            return this;
        }

        public Builder modelId(InternalId val) {
            modelId = val;
            return this;
        }

        public Builder customerId(CustomerPublicId val) {
            customerId = val;
            return this;
        }

        public VehicleCreationCommand build() {
            return new VehicleCreationCommand(this);
        }
    }
}
