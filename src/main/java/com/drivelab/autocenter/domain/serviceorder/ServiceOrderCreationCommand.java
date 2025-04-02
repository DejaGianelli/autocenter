package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.customer.CustomerPublicId;
import com.drivelab.autocenter.domain.vehicle.VehiclePublicId;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class ServiceOrderCreationCommand {

    private final CustomerPublicId customerPublicId;
    private final VehiclePublicId vehiclePublicId;
    private ServiceOrderObservations observations;

    public ServiceOrderCreationCommand(@NonNull CustomerPublicId customerPublicId,
                                       @NonNull VehiclePublicId vehiclePublicId) {
        this.customerPublicId = customerPublicId;
        this.vehiclePublicId = vehiclePublicId;
    }

    public ServiceOrderCreationCommand setObservations(@Nullable ServiceOrderObservations observations) {
        this.observations = observations;
        return this;
    }

    public Optional<ServiceOrderObservations> description() {
        return Optional.ofNullable(observations);
    }

    public CustomerPublicId customerPublicId() {
        return customerPublicId;
    }

    public VehiclePublicId vehiclePublicId() {
        return vehiclePublicId;
    }
}
