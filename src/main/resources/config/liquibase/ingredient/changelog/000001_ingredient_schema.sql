CREATE SCHEMA ingredient;

CREATE TABLE ingredient.ingredient(
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR NOT NULL,
    price numeric(10,2) NOT NULL,
    qty_available INT NOT NULL,
    CONSTRAINT pk_ingredient PRIMARY KEY (id)
);