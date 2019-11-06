CREATE TABLE sensors (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  imm_rfi_id BIGINT NOT NULL,
  sensor VARCHAR(100),
  sensor_type VARCHAR(20),
  mode VARCHAR(100),
  required_quality INTEGER,
  desired_quality INTEGER
);