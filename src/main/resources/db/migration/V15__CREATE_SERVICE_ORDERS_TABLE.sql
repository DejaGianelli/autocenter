CREATE TABLE service_orders (
    internal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    public_id CHAR(26) NOT NULL,
    customer_id BIGINT NOT NULL,
    vehicle_id BIGINT NOT NULL,
    checkin_odometer INT NOT NULL DEFAULT 0,
    status ENUM("waiting_start", "work_in_progress") NOT NULL,
    total INT NOT NULL DEFAULT 0,
    observations VARCHAR(256) NULL,
    created_at_utc TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(internal_id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(internal_id)
);