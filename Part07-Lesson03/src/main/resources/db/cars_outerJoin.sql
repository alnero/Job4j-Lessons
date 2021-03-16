--- create ---
CREATE TABLE Car_body (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE Car_engine (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE Car_transmission (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE Car (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255),
    body_id         INT REFERENCES Car_body (id),
    engine_id       INT REFERENCES Car_engine (id),
    transmission_id INT REFERENCES Car_transmission (id)
);

--- insert ---
INSERT INTO Car_body(name)
VALUES ('Body 1'), ('Body 2'), ('Body 3'), ('Body 4'), ('Body 5');

INSERT INTO Car_engine(name)
VALUES ('Engine 1'), ('Engine 2'), ('Engine 3'), ('Engine 4'), ('Engine 5');

INSERT INTO Car_transmission(name)
VALUES ('Transmission 1'), ('Transmission 2'), ('Transmission 3'), ('Transmission 4'), ('Transmission 5');

INSERT INTO Car(name, body_id, engine_id, transmission_id)
VALUES ('Car 1', 1, 1, 1), ('Car 2', 2, 2, 2), ('Car 5', 5, 5, 5);

--- all cars with all parts ---
SELECT c.name, b.name, e.name, t.name FROM Car c
JOIN Car_body b ON c.body_id = b.id
JOIN Car_engine e ON c.engine_id = e.id
JOIN Car_transmission t ON c.transmission_id = t.id;

--- parts not used in cars ---
SELECT b.name FROM Car_body b LEFT JOIN Car c ON c.body_id = b.id
WHERE c.body_id IS NULL;

SELECT e.name FROM Car_engine e LEFT JOIN Car c ON c.engine_id = e.id
WHERE c.engine_id IS NULL;

SELECT t.name FROM Car_transmission t LEFT JOIN Car c ON c.transmission_id = t.id
WHERE c.transmission_id IS NULL;