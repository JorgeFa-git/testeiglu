INSERT INTO menu.menu_option(id, name, img_src)
VALUES (100001, 'X-Bacon', 'https://foodish-api.com/images/burger/burger2.jpg'),
       (100002, 'X-Burger', 'https://foodish-api.com/images/burger/burger3.jpg'),
       (100003, 'X-Egg', 'https://foodish-api.com/images/burger/burger6.jpg'),
       (100004, 'X-Egg Bacon', 'https://foodish-api.com/images/burger/burger5.jpg');

INSERT INTO menu.menu_option_ingredients(id, ingredient_id, menu_option_id)
VALUES (100001, 100002, 100001),
       (100002, 100002, 100004),
       (100003, 100003, 100001),
       (100004, 100003, 100002),
       (100005, 100003, 100003),
       (100006, 100003, 100004),
       (100007, 100004, 100003),
       (100008, 100004, 100004),
       (100009, 100005, 100001),
       (100010, 100005, 100002),
       (100011, 100005, 100003),
       (100012, 100005, 100004);