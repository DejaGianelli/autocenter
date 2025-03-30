CREATE TABLE purchases (
    internal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    public_id CHAR(26) UNIQUE NOT NULL,
    supplier_id BIGINT NOT NULL,
    total INT NOT NULL DEFAULT 0,
    status ENUM("created", "billed", "received") NOT NULL,
    billed_at_utc TIMESTAMP NULL,
    received_at_utc TIMESTAMP NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(internal_id)
);