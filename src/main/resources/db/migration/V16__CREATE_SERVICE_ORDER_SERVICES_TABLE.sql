CREATE TABLE service_order_services (
    service_order_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    price INT NOT NULL,
    PRIMARY KEY (service_order_id, service_id),
    CONSTRAINT fk_service_orders_services_service_orders FOREIGN KEY (service_order_id) REFERENCES service_orders(internal_id),
    CONSTRAINT fk_service_orders_services_services FOREIGN KEY (service_id) REFERENCES services(internal_id)
);