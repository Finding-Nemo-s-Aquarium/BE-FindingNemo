/*-- Categories 삽입
INSERT INTO categories (name) VALUES ('shop');
INSERT INTO categories (name, parent_id) VALUES ('어항', 1);
INSERT INTO categories (name, parent_id) VALUES ('물고기', 1);
INSERT INTO categories (name, parent_id) VALUES ('수초', 1);
INSERT INTO categories (name, parent_id) VALUES ('관상용 돌', 1);
INSERT INTO categories (name, parent_id) VALUES ('바닥재', 1);
*/
-- Items 삽입
INSERT INTO item (name, price, img_url) VALUES ('Fishtank', 30, '/item/Fishtank.png');
INSERT INTO item (name, price, img_url) VALUES ('Fishbowl', 20, '/item/Fishbowl.png');
INSERT INTO item (name, price, img_url) VALUES ('Guppy', 10, '/item/Guppy.png');
INSERT INTO item (name, price, img_url) VALUES ('Goldfish', 10, '/item/Goldfish.png');
INSERT INTO item (name, price, img_url) VALUES ('Betta', 7, '/item/Betta.png');
INSERT INTO item (name, price, img_url) VALUES ('Angelfish', 28, '/item/Angelfish.png');
INSERT INTO item (name, price, img_url) VALUES ('Tetra', 5, '/item/Tetra.png');
INSERT INTO item (name, price, img_url) VALUES ('Blyxa_japonica', 3, '/item/Blyxa_japonica.png');
INSERT INTO item (name, price, img_url) VALUES ('Water_sprite', 2, '/item/Water_sprite.png');
INSERT INTO item (name, price, img_url) VALUES ('Vallisneria', 4, '/item/Vallisneria.png');
INSERT INTO item (name, price, img_url) VALUES ('Egg_stone', 3, '/item/Egg_stone.png');
INSERT INTO item (name, price, img_url) VALUES ('Blue_dragon_stone', 4, '/item/Blue_dragon_stone.png');
INSERT INTO item (name, price, img_url) VALUES ('Volcanic_stone', 5, '/item/Volcanic_stone.png');
INSERT INTO item (name, price, img_url) VALUES ('Black_sand', 15, '/item/Black_pebble.png');
INSERT INTO item (name, price, img_url) VALUES ('White_sand', 10, '/item/White_pebble.png');
INSERT INTO item (name, price, img_url) VALUES ('Multicolored_sand', 9, '/item/Multicolored_pebble.png');