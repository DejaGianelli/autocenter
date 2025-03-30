CREATE TABLE financial_accounts (
    internal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(64) NOT NULL,
    name VARCHAR(256) NOT NULL
);