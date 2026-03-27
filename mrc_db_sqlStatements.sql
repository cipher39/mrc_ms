# SalesPerson table details

DROP DATABASE mrc_db;
CREATE DATABASE mrc_db;
USE mrc_db;

DROP TABLE salesperson;
CREATE TABLE salesperson(sales_id INT PRIMARY KEY, name VARCHAR(30), salary INT, commission_rate INT, hire_date DATE);
INSERT INTO salesperson (sales_id, name, salary, commission_rate, hire_date) VALUES
(1, 'John', 100000, 6, '2006-04-01'),
(2, 'Amy', 12000, 5, '2010-05-01'),
(3, 'Mark', 65000, 12, '2008-12-25'),
(4, 'Pam', 25000, 25, '2005-01-01'),
(5, 'Alex', 5000, 10, '2007-02-03');

# Company table details
DROP TABLE company;
CREATE TABLE company(com_id INT PRIMARY KEY, name VARCHAR(20), city VARCHAR(20));
INSERT INTO company (com_id, name, city) VALUES
(1, 'RED', 'Boston'),
(2, 'ORANGE', 'New York'),
(3, 'YELLOW', 'Boston'),
(4, 'GREEN', 'Austin');



