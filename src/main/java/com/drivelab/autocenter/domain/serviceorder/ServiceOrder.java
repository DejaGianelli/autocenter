package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.DomainEntity;
import com.drivelab.autocenter.domain.customer.Customer;
import com.drivelab.autocenter.domain.vehicle.Vehicle;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "service_orders")
public class ServiceOrder extends DomainEntity {

    @Embedded
    private ServiceOrderPublicId publicId;

    @Convert(converter = ServiceOrderStatusAttributeConverter.class)
    private ServiceOrderStatus status;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "serviceOrder",
            cascade = CascadeType.ALL
    )
    private final Set<ServiceOrderService> services;

    @Convert(converter = ServiceOrderObservationsAttributeConverter.class)
    private ServiceOrderObservations observations;

    private OffsetDateTime createdAtUtc;

    protected ServiceOrder() {
        this.publicId = new ServiceOrderPublicId();
        this.createdAtUtc = Instant.now().atOffset(ZoneOffset.UTC);
        this.status = ServiceOrderStatus.CREATED;
        this.services = new HashSet<>();
    }

    public ServiceOrder(@NonNull Customer customer, @NonNull Vehicle vehicle) {
        this();
        this.customer = customer;
        this.vehicle = vehicle;
    }

    public ServiceOrder(@NonNull Customer customer,
                        @NonNull Vehicle vehicle,
                        @NonNull ServiceOrderObservations observations) {
        this(customer, vehicle);
        this.observations = observations;
    }

    public void setObservations(@NonNull ServiceOrderObservations description) {
        this.observations = description;
    }

    public Vehicle vehicle() {
        return vehicle;
    }

    public Customer customer() {
        return customer;
    }

    public Optional<ServiceOrderObservations> observations() {
        return Optional.ofNullable(observations);
    }

    public ServiceOrderPublicId publicId() {
        return publicId;
    }

    public ServiceOrderStatus status() {
        return status;
    }

    public OffsetDateTime createdAtUtc() {
        return createdAtUtc;
    }

    public Set<ServiceOrderService> services() {
        return services;
    }
}
