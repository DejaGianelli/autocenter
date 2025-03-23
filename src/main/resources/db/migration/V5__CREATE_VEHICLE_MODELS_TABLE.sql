CREATE TABLE vehicle_models (
    internal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id BIGINT NOT NULL,
    name VARCHAR(32),
    FOREIGN KEY (brand_id) REFERENCES vehicle_brands(internal_id)
);
