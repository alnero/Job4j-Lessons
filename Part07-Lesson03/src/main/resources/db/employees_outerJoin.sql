--- create ---
CREATE TABLE departments (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE employees (
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(255),
    gender  VARCHAR(255),
    dept_id INT REFERENCES departments (id)
);

--- insert ---
INSERT INTO departments (name)
VALUES ('Sales'),
       ('Finance'),
       ('Accounts'),
       ('Marketing');

INSERT INTO employees (name, gender, dept_id)
VALUES ('Alan', 'male', 1),
       ('Bob', 'male', 2),
       ('Charlie', 'male', 3),
       ('Dan', 'male', 3),
       ('Elizabeth', 'female', null),
       ('Fiona', 'female', null);


--- left, right, full, cross joins between employees and departments ---
SELECT * FROM departments d LEFT JOIN employees e ON e.dept_id = d.id;
SELECT * FROM employees e RIGHT JOIN departments d ON e.dept_id = d.id;

SELECT * FROM employees e LEFT JOIN departments d ON e.dept_id = d.id;
SELECT * FROM departments d RIGHT JOIN employees e ON e.dept_id = d.id;

SELECT * FROM departments d FULL JOIN employees e ON e.dept_id = d.id;
SELECT * FROM employees e FULL JOIN departments d ON e.dept_id = d.id;

SELECT * FROM departments CROSS JOIN employees;
SELECT * FROM employees CROSS JOIN departments;

--- departments without employees ---
SELECT * FROM departments d LEFT JOIN employees e ON e.dept_id = d.id
WHERE e.dept_id IS NULL;

--- employees without departments ---
SELECT * FROM employees e LEFT JOIN departments d ON e.dept_id = d.id
WHERE e.dept_id IS NULL;
SELECT * FROM employees e LEFT JOIN departments d ON e.dept_id = d.id
WHERE d.id IS NULL;

--- all different gender employees pairs ---
SELECT e1.name AS name, e2.name AS name, CONCAT(e1.gender, '+', e2.gender) AS couple
FROM employees e1 CROSS JOIN employees e2
WHERE e1.gender != e2.gender;