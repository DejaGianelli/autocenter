package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.MoneyAttributeConverter;
import com.drivelab.autocenter.domain.service.Service;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "service_order_services")
public class ServiceOrderService {

    @EmbeddedId
    private final ServiceOrderServiceId id;

    @MapsId("serviceOrderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_order_id")
    private ServiceOrder serviceOrder;

    @Fetch(FetchMode.JOIN)
    @MapsId("serviceId")
    @JoinColumn(name = "service_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Service service;

    @Convert(converter = MoneyAttributeConverter.class)
    private Money cost;

    @Convert(converter = ServiceDescriptionAttributeConverter.class)
    private ServiceDescription description;

    protected ServiceOrderService() {
        this.id = new ServiceOrderServiceId();
        this.cost = Money.ZERO;
    }

    public ServiceOrderService(@NonNull Service service,
                               @NonNull Money cost,
                               @NonNull ServiceDescription description) {
        this();
        this.service = service;
        this.cost = cost;
        this.description = description;
    }

    public ServiceOrderServiceId id() {
        return id;
    }

    public ServiceOrder serviceOrder() {
        return serviceOrder;
    }

    public Service service() {
        return service;
    }

    public Money cost() {
        return cost;
    }

    public ServiceDescription description() {
        return description;
    }
}

