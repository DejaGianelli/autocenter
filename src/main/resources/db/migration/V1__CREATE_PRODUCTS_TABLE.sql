CREATE TABLE products (
    internal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    public_id CHAR(26) UNIQUE NOT NULL,
    sku VARCHAR(32) NOT NULL,
    name VARCHAR(64) NOT NULL
);
