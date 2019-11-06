CREATE TABLE rfi_service_events (
  rfi_id BINARY(16) NOT NULL,
  version INT NOT NULL,
  event_type TEXT NOT NULL,
  event_data LONGTEXT NOT NULL,
  user_name VARCHAR(100) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  PRIMARY KEY(rfi_id, version)
)