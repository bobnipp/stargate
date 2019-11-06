ALTER TABLE users
  ADD COLUMN user_name VARCHAR(255);

UPDATE users
SET user_name = first_name;

ALTER TABLE users
  MODIFY COLUMN user_name VARCHAR(255) NOT NULL;
