CREATE TABLE vehicles (
    internal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    public_id CHAR(26) UNIQUE NOT NULL,
    model_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    plate CHAR(7) NOT NULL,
    FOREIGN KEY (model_id) REFERENCES vehicle_models(internal_id),
    FOREIGN KEY (customer_id) REFERENCES customers(internal_id)
);