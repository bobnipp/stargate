CREATE TABLE requests (
  id VARCHAR(36) NOT NULL,
  title VARCHAR(100),
  objective VARCHAR(100),
  description VARCHAR(100),
  request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)