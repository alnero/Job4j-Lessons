CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    qty INTEGER,
    available BOOLEAN,
    from_date DATE
 );

INSERT INTO items (name, qty, available, from_date)
VALUES ('pencil', 11, true, '2021-03-10');

SELECT * FROM items;

UPDATE items SET name = 'pen', qty = 22;

SELECT * FROM items;

DELETE FROM items;

SELECT * FROM items;