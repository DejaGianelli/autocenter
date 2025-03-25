CREATE TABLE customers (
    internal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    public_id CHAR(26) UNIQUE NOT NULL,
    type ENUM("legal", "natural") NOT NULL,
    cpf CHAR(11) NULL,
    cnpj CHAR(14) NULL
);

ALTER TABLE customers
ADD CONSTRAINT customer_cpf_check
CHECK (
    CASE
        WHEN type = 'natural'
        THEN
            CASE
                WHEN cpf IS NOT NULL
                THEN 1
                ELSE 0
            END
        ELSE 1
    END = 1
);

ALTER TABLE customers
ADD CONSTRAINT customer_cnpj_check
CHECK (
    CASE
        WHEN type = 'legal'
        THEN
            CASE
                WHEN cnpj IS NOT NULL
                THEN 1
                ELSE 0
            END
        ELSE 1
    END = 1
);