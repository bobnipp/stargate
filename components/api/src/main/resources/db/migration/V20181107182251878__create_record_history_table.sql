CREATE TABLE record_history(
  username VARCHAR(100) NOT NULL,
  action VARCHAR(100) NOT NULL,
  rfi_id BIGINT(20) NOT NULL,
  date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE INDEX idx_rfi_id ON record_history(rfi_id);