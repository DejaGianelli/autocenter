package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.InternalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, InternalId> {

    Optional<Vehicle> findByPlate(Plate plate);

    Optional<Vehicle> findByPublicId(VehiclePublicId publicId);
}
