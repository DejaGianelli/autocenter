CREATE TABLE vehicles (
    internal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    model_id BIGINT NOT NULL,
    plate CHAR(7) NOT NULL,
    FOREIGN KEY (model_id) REFERENCES vehicle_models(internal_id)
);