CREATE TABLE role (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE "user" (
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(255),
    role_id INT REFERENCES role (id)
);

CREATE TABLE authority (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE role_authority (
    id           SERIAL PRIMARY KEY,
    role_id      INT REFERENCES role (id),
    authority_id INT REFERENCES authority (id)
);

CREATE TABLE category (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE state (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE item (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255),
    user_id     INT REFERENCES "user" (id),
    category_id INT REFERENCES category (id),
    state_id    INT REFERENCES state (id)
);

CREATE TABLE comment (
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(255),
    item_id INT REFERENCES item (id)
);

CREATE TABLE attachment (
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(255),
    item_id INT REFERENCES item (id)
);