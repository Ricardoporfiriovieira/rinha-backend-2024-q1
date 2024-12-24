-- Criação da tabela customers
CREATE TABLE IF NOT EXISTS customers (
     id INT PRIMARY KEY,
     credit_limit INT,
     balance INT
);

INSERT INTO customers (id, credit_limit , balance) VALUES
(1, 100000, 0),
(2, 80000, 0),
(3, 1000000, 0),
(4, 10000000, 0),
(5, 500000, 0);