-- Departments
insert into department (id, name, budget) values
(1, 'Engineering', 1000000),
(2, 'HR', 200000),
(3, 'Marketing', 500000);

-- Employees (manager_id can be null or point to an existing employee)
insert into employee (id, name, email, department_id, date_of_joining, salary, manager_id) values
(1, 'Alice', 'alice@example.com', 1, '2023-01-10', 150000, null),
(2, 'Bob',   'bob@example.com',   1, '2023-04-01', 120000, 1),
(3, 'Cara',  'cara@example.com',  2, '2022-06-20',  90000, 1);

-- Projects
insert into project (id, name, start_date, end_date, department_id) values
(10, 'Phoenix', '2024-01-01', null, 1),
(11, 'Hermes',  '2024-02-01', null, 1),
(12, 'OnboardingRevamp', '2024-03-01', null, 2);

-- Employee↔Project (bridge with extra fields)
insert into employee_project (employee_id, project_id, assigned_date, role) values
(1, 10, '2024-01-10', 'Lead'),
(1, 11, '2024-02-05', 'Reviewer'),
(2, 10, '2024-03-01', 'Developer'),
(3, 12, '2024-04-01', 'Coordinator');

-- Reviews
insert into performance_review (id, employee_id, review_date, score, review_comments) values
(100, 1, '2024-12-31', 5, 'Outstanding'),
(101, 1, '2025-06-30', 4, 'Great progress'),
(102, 1, '2025-03-31', 5, 'Excellent initiative'),
(103, 2, '2024-12-31', 3, 'Solid contributor'),
(104, 3, '2025-06-30', 4, 'Improving steadily');
