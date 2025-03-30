CREATE TABLE accounts_payable (
    internal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    public_id CHAR(26) NOT NULL,
    status ENUM("pending", "paid", "overdue") NOT NULL,
    amount INT NOT NULL DEFAULT 0,
    due_date DATE NOT NULL,
    paid_at_utc TIMESTAMP NULL,
    payment_method ENUM("cash", "credit_card", "debit_card", "check", "pix") NULL,
    origin_id VARCHAR(36) NOT NULL,
    origin_type ENUM("purchase") NOT NULL,
    created_at_utc TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);