CREATE TABLE purchase_items (
    purchase_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    received_quantity INT NOT NULL DEFAULT 0,
    unit_cost INT NOT NULL,
    PRIMARY KEY (purchase_id, product_id),
    CONSTRAINT fk_purchase_items_purchase FOREIGN KEY (purchase_id) REFERENCES purchases(internal_id),
    CONSTRAINT fk_purchase_items_product FOREIGN KEY (product_id) REFERENCES products(internal_id)
);