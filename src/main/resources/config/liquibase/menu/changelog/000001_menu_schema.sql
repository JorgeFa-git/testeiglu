CREATE SCHEMA menu;

CREATE TABLE menu.menu_option(
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR NOT NULL,
    img_src VARCHAR NOT NULL,
    CONSTRAINT pk_menu_option PRIMARY KEY (id)
);

CREATE TABLE menu.menu_option_ingredients(
    id BIGINT AUTO_INCREMENT NOT NULL,
    menu_option_id BIGINT NOT NULL,
    ingredient_id BIGINT NOT NULL,
    CONSTRAINT pk_menu_option_ingredient PRIMARY KEY (id),
    CONSTRAINT fk_menu_option_ingredient_menu_option_id FOREIGN KEY (menu_option_id) REFERENCES menu.menu_option (id),
    CONSTRAINT fk_menu_option_ingredient_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES ingredient.ingredient (id)
);