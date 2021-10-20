-- names of employees excluding employees from company with id = 5 and name of company for each person
SELECT p.name as person_name, c.name as company_name
FROM person p
LEFT JOIN company c
ON p.company_id = c.id
WHERE p.company_id != 5;

-- name of company and qty of employees for company with max qty of employees
SELECT c.name as company_name, count(p.name) as qty_employee
FROM person p
LEFT JOIN company c
ON c.id = p.company_id
GROUP BY c.name
ORDER BY qty_employee DESC
LIMIT 1