CREATE TABLE accounting_entries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_category VARCHAR(64) NOT NULL,
    type ENUM("debit", "credit") NOT NULL,
    debit INT DEFAULT 0,
    credit INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE accounting_entries
ADD CONSTRAINT debit_check
CHECK (
    CASE
        WHEN type = 'debit'
        THEN
            CASE
                WHEN debit IS NOT NULL
                THEN 1
                ELSE 0
            END
        ELSE 1
    END = 1
);

ALTER TABLE accounting_entries
ADD CONSTRAINT credit_check
CHECK (
    CASE
        WHEN type = 'credit'
        THEN
            CASE
                WHEN credit IS NOT NULL
                THEN 1
                ELSE 0
            END
        ELSE 1
    END = 1
);