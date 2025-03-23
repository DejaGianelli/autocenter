CREATE TABLE inventory_movements (
    internal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    inventory_id BIGINT NOT NULL,
    type ENUM('withdraw', 'entry') NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (inventory_id) REFERENCES inventory(internal_id)
);
