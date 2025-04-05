package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.customer.Customer;
import com.drivelab.autocenter.domain.customer.CustomerNotFoundException;
import com.drivelab.autocenter.domain.customer.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class VehicleCreationUseCase {

    private final VehicleRepository vehicleRepository;
    private final VehicleModelRepository vehicleModelRepository;
    private final CustomerRepository customerRepository;

    public VehicleCreationUseCase(VehicleRepository vehicleRepository,
                                  VehicleModelRepository vehicleModelRepository,
                                  CustomerRepository customerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleModelRepository = vehicleModelRepository;
        this.customerRepository = customerRepository;
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

        Optional<Customer> optionalCustomer = customerRepository.findByPublicId(command.customerId());
        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException(command.customerId());
        }

        Vehicle vehicle = Vehicle.Builder.builder()
                .plate(command.plate())
                .model(optionalModel.get())
                .customer(optionalCustomer.get())
                .build();

        vehicle = vehicleRepository.saveAndFlush(vehicle);

        return vehicle;
    }
}
