ALTER TABLE requests ADD CONSTRAINT PRIMARY KEY(id);

CREATE TABLE comments (
  id BINARY(16) NOT NULL,
  request_id VARCHAR(36) NOT NULL,
  content VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  FOREIGN KEY (request_id) REFERENCES requests(id)
)