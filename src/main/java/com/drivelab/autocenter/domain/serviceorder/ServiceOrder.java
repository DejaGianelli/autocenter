package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.DomainEntity;
import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.MoneyAttributeConverter;
import com.drivelab.autocenter.domain.customer.Customer;
import com.drivelab.autocenter.domain.vehicle.Odometer;
import com.drivelab.autocenter.domain.vehicle.Vehicle;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AttributeOverrides({
        @AttributeOverride(name = "checkInOdometer.value", column = @Column(name = "checkin_odometer", nullable = false))
})
@Entity
@Table(name = "service_orders")
public class ServiceOrder extends DomainEntity {

    @Embedded
    private final ServiceOrderPublicId publicId;

    @Convert(converter = ServiceOrderStatusAttributeConverter.class)
    private ServiceOrderStatus status;

    @Embedded
    private Odometer checkInOdometer;

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

    @Convert(converter = MoneyAttributeConverter.class)
    private Money total;

    private final OffsetDateTime createdAtUtc;

    protected ServiceOrder() {
        this.publicId = new ServiceOrderPublicId();
        this.createdAtUtc = Instant.now().atOffset(ZoneOffset.UTC);
        this.status = ServiceOrderStatus.WAITING_START;
        this.services = new HashSet<>();
        this.total = Money.ZERO;
    }

    public ServiceOrder(@NonNull Customer customer, @NonNull Vehicle vehicle, @NonNull Odometer checkInOdometer) {
        this();
        this.customer = customer;
        this.vehicle = vehicle;
        this.checkInOdometer = checkInOdometer;
    }

    public ServiceOrder(@NonNull Customer customer,
                        @NonNull Vehicle vehicle,
                        @NonNull Odometer checkInOdometer,
                        @NonNull ServiceOrderObservations observations) {
        this(customer, vehicle, checkInOdometer);
        this.observations = observations;
    }

    public void addItem(@NonNull ServiceOrderService service) {
        service.setServiceOrder(this);
        for (ServiceOrderService s : services) {
            if (s.equals(service)) {
                s.update(service);
                updateTotal();
                return;
            }
        }
        services.add(service);
        updateTotal();
    }

    public Money totalServiceCost() {
        int total = 0;
        for (ServiceOrderService service : services) {
            total += service.price().cents();
        }
        return Money.create(total);
    }

    private void updateTotal() {
        this.total = totalServiceCost();
    }

    public void moveToInProgress() {
        ServiceOrderStatus to = ServiceOrderStatus.WORK_IN_PROGRESS;
        if (!this.status.canChangeTo(to)) {
            throw new ServiceOrderInvalidStatusChangeException(this.status, to);
        }
        this.status = to;
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

    public Odometer checkInOdometer() {
        return checkInOdometer;
    }

    public Money total() {
        return total;
    }
}
