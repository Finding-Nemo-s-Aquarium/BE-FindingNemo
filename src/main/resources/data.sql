-- Categories 삽입
INSERT INTO categories (name, parent_id) VALUES ('shop', NULL);
INSERT INTO categories (name, parent_id) VALUES ('어항', 1);
INSERT INTO categories (name, parent_id) VALUES ('물고기', 1);
INSERT INTO categories (name, parent_id) VALUES ('수초', 1);
INSERT INTO categories (name, parent_id) VALUES ('관상용 돌', 1);
INSERT INTO categories (name, parent_id) VALUES ('바닥재', 1);

-- Items 삽입
INSERT INTO items (name, price, category_id) VALUES ('사각어항', 30, 2);
INSERT INTO items (name, price, category_id) VALUES ('둥근어항', 20, 2);
INSERT INTO items (name, price, category_id) VALUES ('구피', 10, 3);
INSERT INTO items (name, price, category_id) VALUES ('금붕어', 10, 3);
INSERT INTO items (name, price, category_id) VALUES ('베타', 7, 3);
INSERT INTO items (name, price, category_id) VALUES ('앤젤피쉬', 28, 3);
INSERT INTO items (name, price, category_id) VALUES ('테트라', 5, 3);
INSERT INTO items (name, price, category_id) VALUES ('브릭샤 자포니카', 3, 4);
INSERT INTO items (name, price, category_id) VALUES ('아메리카 워터스프라이트', 2, 4);
INSERT INTO items (name, price, category_id) VALUES ('발리스네리아', 4, 4);
INSERT INTO items (name, price, category_id) VALUES ('에그스톤(1kg)', 3, 5);
INSERT INTO items (name, price, category_id) VALUES ('청룡석(1kg)', 4, 5);
INSERT INTO items (name, price, category_id) VALUES ('화산석(1kg)', 5, 5);
INSERT INTO items (name, price, category_id) VALUES ('흑사', 15, 6);
INSERT INTO items (name, price, category_id) VALUES ('백사', 10, 6);
INSERT INTO items (name, price, category_id) VALUES ('오색사', 9, 6);