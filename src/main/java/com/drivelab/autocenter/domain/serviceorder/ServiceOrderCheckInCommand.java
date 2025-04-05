package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.customer.CustomerPublicId;
import com.drivelab.autocenter.domain.vehicle.Odometer;
import com.drivelab.autocenter.domain.vehicle.VehiclePublicId;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class ServiceOrderCheckInCommand {

    private final CustomerPublicId customerPublicId;
    private final VehiclePublicId vehiclePublicId;
    private final Odometer odometer;
    private ServiceOrderObservations observations;

    public ServiceOrderCheckInCommand(@NonNull CustomerPublicId customerPublicId,
                                      @NonNull VehiclePublicId vehiclePublicId,
                                      @NonNull Odometer odometer) {
        this.customerPublicId = customerPublicId;
        this.vehiclePublicId = vehiclePublicId;
        this.odometer = odometer;
    }

    public ServiceOrderCheckInCommand setObservations(@Nullable ServiceOrderObservations observations) {
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

    public Odometer odometer() {
        return odometer;
    }
}
