INSERT INTO role(name)
VALUES ('admin'),
       ('user');

INSERT INTO "user"(name, role_id)
VALUES ('Bob', 1),
       ('Alice', 2);

INSERT INTO authority(name)
VALUES ('CREATE'),
       ('READ'),
       ('UPDATE'),
       ('DELETE');

INSERT INTO role_authority(role_id, authority_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4);
INSERT INTO role_authority(role_id, authority_id)
VALUES (2, 2);

INSERT INTO category(name)
VALUES ('test'),
       ('production');

INSERT INTO state(name)
VALUES ('open'),
       ('closed');

INSERT INTO item(name, user_id, category_id, state_id)
VALUES ('Read tests', 2, 1, 2),
       ('Start server', 1, 2, 1);

INSERT INTO comment(name, item_id)
VALUES ('Test comment 1', 1),
       ('Test comment 2', 1);
INSERT INTO comment(name, item_id)
VALUES ('Server comment 1', 2),
       ('Server comment 2', 2);

INSERT INTO attachment(name, item_id)
VALUES ('Test attachment 1', 1),
       ('Test attachment 2', 1);
INSERT INTO attachment(name, item_id)
VALUES ('Server attachment 1', 2),
       ('Server attachment 2', 2);