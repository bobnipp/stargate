ALTER TABLE users
ADD COLUMN id VARCHAR(36) NOT NULL FIRST,
CHANGE COLUMN name first_name VARCHAR(100) NOT NULL,
ADD COLUMN last_name VARCHAR(100) NOT NULL,
ADD COLUMN email VARCHAR(100) NOT NULL,
ADD COLUMN notes VARCHAR(255)
