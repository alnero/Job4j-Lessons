--- create ---
CREATE TABLE type (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE product (
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255),
    expired_date DATE,
    price        FLOAT,
    type_id      INT REFERENCES type (id)
);

--- insert ---
INSERT INTO type (name)
VALUES ('CHEESE'), ('ICE-CREAM'), ('MILK');

INSERT INTO product (name, type_id, expired_date, price)
VALUES ('Milk 1', 3, '17-03-2021', 1.0),
       ('Milk 2', 3, '17-03-2021', 2.0),
       ('Milk 3', 3, '17-03-2021', 3.0),
       ('Milk 4', 3, '17-03-2021', 4.0),
       ('Milk 5', 3, '19-03-2021', 5.0),
       ('Milk 6', 3, '19-03-2021', 6.0),
       ('Milk 6', 3, '19-03-2021', 7.0),
       ('Milk 7', 3, '19-03-2021', 8.0),
       ('Milk 9', 3, '21-03-2021', 9.0),
       ('Milk 10', 3, '21-03-2021', 10.0),
       ('Milk 11', 3, '21-03-2021', 11.0),
       ('Milk 12', 3, '21-03-2021', 12.0),

       ('Ice-cream 1', 2, '12-09-2021', 21.0),
       ('Ice-cream 2', 2, '12-09-2021', 22.0),
       ('Ice-cream 3', 2, '12-09-2021', 23.0),
       ('Ice-cream 4', 2, '12-09-2021', 24.0),
       ('Ice-cream 5', 2, '12-03-2022', 25.0),
       ('Ice-cream 6', 2, '12-03-2022', 26.0),
       ('Ice-cream 7', 2, '12-03-2022', 27.0),
       ('Ice-cream 8', 2, '12-03-2022', 28.0),

       ('Cheese 1', 1, '12-04-2020', 31.0),
       ('Cheese 2', 1, '12-05-2020', 32.0),
       ('Cheese 3', 1, '12-06-2020', 33.0),
       ('Cheese 4', 1, '12-07-2020', 34.0);

--- filtering ---
--- all products with type cheese ---
SELECT p.id, p.name, p.type_id, p.expired_date, p.price FROM product p
JOIN type t ON p.type_id = t.id
WHERE t.name = 'CHEESE';

--- all products with 'Ice-cream' in name ---
SELECT * FROM product WHERE name LIKE '%Ice-cream%';

--- all products that expire next month ---
SELECT * FROM product WHERE expired_date > '01-04-2021' AND expired_date < '30-04-2021';

--- all products with max price ---
SELECT * FROM product WHERE price = (SELECT MAX(price) FROM product);

--- qty of products by type ---
SELECT t.name, COUNT(*) FROM product p
JOIN type t ON p.type_id = t.id
GROUP BY t.id;

--- all products with type cheese and type milk ---
SELECT p.id, p.name, p.type_id, t.name, p.expired_date, p.price FROM product p
JOIN type t ON p.type_id = t.id
WHERE t.name = 'CHEESE' OR t.name = 'MILK';

--- qty of products by type, less than 10 pcs ---
SELECT t.name, COUNT(*) FROM product p
JOIN type t ON p.type_id = t.id
GROUP BY t.id
HAVING COUNT(*) < 10;

--- all products and their types ---
SELECT p.id, p.name, p.type_id, t.name AS type_name, p.expired_date, p.price FROM product p
JOIN type t ON p.type_id = t.id;