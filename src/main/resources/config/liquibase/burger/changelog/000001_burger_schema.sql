CREATE SCHEMA burger;

CREATE TABLE burger.burger(
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR NOT NULL,
    CONSTRAINT pk_burger PRIMARY KEY (id)
);

CREATE TABLE burger.burger_ingredients(
    id BIGINT AUTO_INCREMENT NOT NULL,
    burger_id BIGINT NOT NULL,
    ingredient_id BIGINT NOT NULL,
    CONSTRAINT pk_burger_ingredients PRIMARY KEY (id),
    CONSTRAINT fk_burger_ingredients_burger_id FOREIGN KEY (burger_id) REFERENCES burger.burger (id),
    CONSTRAINT fk_burger_ingredients_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES ingredient.ingredient (id)
);

CREATE TYPE burger.discount as ENUM('LIGHT', 'MUITA_CARNE', 'MUITO_QUEIJO');

CREATE TABLE burger.burger_discounts(
    id BIGINT AUTO_INCREMENT NOT NULL,
    burger_id BIGINT NOT NULL,
    discount burger.discount NULL,
    CONSTRAINT pk_burger_discounts PRIMARY KEY (id),
    CONSTRAINT fk_burger_discounts_burger_id FOREIGN KEY (burger_id) REFERENCES burger.burger (id)
);