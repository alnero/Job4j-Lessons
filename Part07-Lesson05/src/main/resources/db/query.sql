-- names of employees excluding employees from company with id = 5 and name of company for each person
SELECT p.name AS person_name, c.name AS company_name
FROM person p
LEFT JOIN company c
ON p.company_id = c.id
WHERE p.company_id != 5;

-- name of company and qty of employees for company with max qty of employees
SELECT c.name AS company_name, COUNT(p.name) AS qty_employee
FROM person p LEFT JOIN company c ON c.id = p.company_id
GROUP BY c.name
ORDER BY qty_employee DESC
LIMIT 1;

-- name of company and qty of employees for company with max qty of employees (if more than one company)
SELECT c.name AS company_name, COUNT(p.name) AS qty_employee
FROM person p LEFT JOIN company c ON c.id = p.company_id
GROUP BY c.name
HAVING COUNT(p.name) = (
    SELECT MAX(sq.qty_employee) FROM (
        SELECT COUNT(*) AS qty_employee FROM person p GROUP BY p.company_id
    ) as sq
);