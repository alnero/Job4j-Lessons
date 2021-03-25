CREATE TABLE task (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR,
    description VARCHAR,
    create_date TIMESTAMP
);

CREATE TABLE comment (
    id          SERIAL PRIMARY KEY,
    content     VARCHAR,
    create_date TIMESTAMP,
    task_id     INT REFERENCES task (id)
);