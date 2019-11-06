# Drop data
DELETE FROM comments;
DELETE FROM requests;
DELETE FROM users;

# Remove FK
ALTER TABLE comments
  DROP FOREIGN KEY comments_ibfk_1;

# Rename requests to RFIs and update data type
ALTER TABLE comments
  CHANGE COLUMN request_id rfi_id INT;
RENAME TABLE
    requests TO rfis;

# Change UUIDs to Integers, set auto increment and primary key
ALTER TABLE comments
  MODIFY id INT AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE rfis
  MODIFY id INT AUTO_INCREMENT;
ALTER TABLE users
  MODIFY id INT AUTO_INCREMENT PRIMARY KEY;

# Re-add and name FK
ALTER TABLE comments
  ADD CONSTRAINT comments_rfis_fk FOREIGN KEY (rfi_id) REFERENCES rfis (id);
