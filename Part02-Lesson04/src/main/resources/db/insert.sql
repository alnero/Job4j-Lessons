INSERT INTO task(id, name, description, create_date) VALUES
(1, 'Task 1', 'Task 1 Description', '01-01-2000'),
(2, 'Task 2', 'Task 2 Description', '02-01-2000'),
(3, 'Task 3', 'Task 3 Description', '03-01-2000');

INSERT INTO comment(id, content, create_date, task_id) VALUES
(1, 'Comment 1', '01-01-2000', 1),
(2, 'Comment 2', '02-01-2000', 2),
(3, 'Comment 3', '02-01-2000', 2),
(4, 'Comment 4', '03-01-2000', 3),
(5, 'Comment 5', '03-01-2000', 3),
(6, 'Comment 6', '03-01-2000', 3);