--- create ---
create table devices (
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people (
    id   serial primary key,
    name varchar(255)
);

create table devices_people (
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

--- insert ---
INSERT INTO devices (name, price) VALUES ('Phone', 4000);
INSERT INTO devices (name, price) VALUES ('Phone', 5000);
INSERT INTO devices (name, price) VALUES ('Phone', 6000);

INSERT INTO devices (name, price) VALUES ('Tablet', 6000);
INSERT INTO devices (name, price) VALUES ('Tablet', 7000);
INSERT INTO devices (name, price) VALUES ('Tablet', 8000);

INSERT INTO devices (name, price) VALUES ('Notebook', 8000);
INSERT INTO devices (name, price) VALUES ('Notebook', 9000);
INSERT INTO devices (name, price) VALUES ('Notebook', 10000);

INSERT INTO people (name) VALUES ('Ann');
INSERT INTO people (name) VALUES ('Bob');
INSERT INTO people (name) VALUES ('Charlie');

INSERT INTO devices_people (device_id, people_id) VALUES (1, 1);
INSERT INTO devices_people (device_id, people_id) VALUES (4, 1);
INSERT INTO devices_people (device_id, people_id) VALUES (7, 1);

INSERT INTO devices_people (device_id, people_id) VALUES (2, 2);
INSERT INTO devices_people (device_id, people_id) VALUES (5, 2);
INSERT INTO devices_people (device_id, people_id) VALUES (8, 2);

INSERT INTO devices_people (device_id, people_id) VALUES (3, 3);
INSERT INTO devices_people (device_id, people_id) VALUES (6, 3);
INSERT INTO devices_people (device_id, people_id) VALUES (9, 3);

--- group by and avg function ---

SELECT AVG(price) AS average_price FROM devices;

SELECT d.name, AVG(d.price) FROM devices AS d GROUP BY d.name;

SELECT p.name, AVG(d.price)
FROM devices_people AS d_p
JOIN people AS p ON d_p.people_id = p.id
JOIN devices AS d ON d_p.device_id = d.id
GROUP BY p.name;

SELECT p.name, AVG(d.price)
FROM devices_people AS d_p
JOIN people AS p ON d_p.people_id = p.id
JOIN devices AS d ON d_p.device_id = d.id
GROUP BY p.name
HAVING AVG(d.price) > 6000;