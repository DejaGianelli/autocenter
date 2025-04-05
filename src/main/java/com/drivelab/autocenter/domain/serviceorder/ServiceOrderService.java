package com.drivelab.autocenter.domain.serviceorder;

import com.drivelab.autocenter.domain.Money;
import com.drivelab.autocenter.domain.MoneyAttributeConverter;
import com.drivelab.autocenter.domain.service.Service;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.NonNull;

import java.util.Objects;

@Entity
@Table(name = "service_order_services")
public class ServiceOrderService {

    @EmbeddedId
    private ServiceOrderServiceId id;

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
    private Money price;

    protected ServiceOrderService() {
        this.id = new ServiceOrderServiceId();
        this.price = Money.ZERO;
    }

    public ServiceOrderService(@NonNull Service service,
                               @NonNull Money price) {
        this();
        this.service = service;
        this.price = price;
        this.id = new ServiceOrderServiceId(service.internalId().value());
    }

    public void setServiceOrder(@NonNull ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
        this.id.setServiceOrderId(serviceOrder.internalId().value());
    }

    public void update(@NonNull ServiceOrderService service) {
        this.price = service.price;
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

    public Money price() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOrderService service = (ServiceOrderService) o;
        return Objects.equals(id, service.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

