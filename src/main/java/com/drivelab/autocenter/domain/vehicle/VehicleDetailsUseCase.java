package com.drivelab.autocenter.domain.vehicle;

import org.hibernate.Hibernate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class VehicleDetailsUseCase {

    private final VehicleRepository vehicleRepository;

    public VehicleDetailsUseCase(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle vehicle(@NonNull VehiclePublicId publicId) {
        Optional<Vehicle> optional = vehicleRepository.findByPublicId(publicId);
        if (optional.isEmpty()) {
            throw new VehicleNotFoundException(publicId);
        }
        Vehicle vehicle = optional.get();
        Hibernate.initialize(vehicle.model());
        Hibernate.initialize(vehicle.customer());
        return vehicle;
    }
}
