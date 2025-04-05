package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.customer.Customer;
import com.drivelab.autocenter.domain.customer.CustomerNotFoundException;
import com.drivelab.autocenter.domain.customer.CustomerRepository;
import com.drivelab.autocenter.domain.vehicle.Vehicle;
import com.drivelab.autocenter.domain.vehicle.VehicleNotFoundException;
import com.drivelab.autocenter.domain.vehicle.VehicleRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ServiceOrderCheckInUseCase {

    private final ServiceOrderRepository serviceOrderRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    public ServiceOrderCheckInUseCase(ServiceOrderRepository serviceOrderRepository,
                                      CustomerRepository customerRepository,
                                      VehicleRepository vehicleRepository) {
        this.customerRepository = customerRepository;
        this.serviceOrderRepository = serviceOrderRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public ServiceOrder checkedIn(@NonNull ServiceOrderCheckInCommand command) {
        Optional<Customer> optionalCustomer = customerRepository.findByPublicId(command.customerPublicId());
        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException(command.customerPublicId());
        }
        Customer customer = optionalCustomer.get();

        Optional<Vehicle> optionalVehicle = vehicleRepository.findByPublicId(command.vehiclePublicId());
        if (optionalVehicle.isEmpty()) {
            throw new VehicleNotFoundException(command.vehiclePublicId());
        }
        Vehicle vehicle = optionalVehicle.get();

        ServiceOrder serviceOrder = new ServiceOrder(customer, vehicle, command.odometer());
        if (command.description().isPresent()) {
            serviceOrder.setObservations(command.description().get());
        }
        return serviceOrderRepository.save(serviceOrder);
    }
}
