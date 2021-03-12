SELECT r.name AS Role, a.name AS Authority
FROM role_authority ra
JOIN role r ON ra.role_id = r.id
JOIN authority a ON ra.authority_id = a.id;

SELECT i.id AS Item_id, i.name AS Item_name, s.name AS Item_state
FROM item i
JOIN state s ON i.state_id = s.id
WHERE s.name = 'closed';

SELECT
i.id AS Id,
i.name AS Item,
u.name AS User,
c.name AS Category,
s.name AS State
FROM item i
JOIN "user" u ON i.user_id = u.id
JOIN category c ON i.category_id = c.id
JOIN state s ON i.state_id = s.id;