package com.drivelab.autocenter.domain.vehicle;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class VehicleCreationUseCase {

    private final VehicleRepository vehicleRepository;
    private final VehicleModelRepository vehicleModelRepository;

    public VehicleCreationUseCase(VehicleRepository vehicleRepository, VehicleModelRepository vehicleModelRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleModelRepository = vehicleModelRepository;
    }

    public Vehicle newVehicle(VehicleCreationCommand command) {
        Optional<Vehicle> optional = vehicleRepository.findByPlate(command.plate());
        if (optional.isPresent()) {
            throw new ExistentVehicleException(command.plate());
        }

        Optional<VehicleModel> optionalModel = vehicleModelRepository.findById(command.modelId().value());
        if (optionalModel.isEmpty()) {
            throw new VehicleModelNotFoundException(command.modelId());
        }

        Vehicle vehicle = new Vehicle(command.plate(), optionalModel.get());
        vehicle = vehicleRepository.saveAndFlush(vehicle);

        return vehicle;
    }
}
