CREATE TABLE suppliers (
    internal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    public_id CHAR(26) UNIQUE NOT NULL,
    name VARCHAR(64) NOT NULL,
    cnpj CHAR(14) NOT NULL
);