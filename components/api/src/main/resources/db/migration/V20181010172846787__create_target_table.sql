CREATE TABLE targets (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  imm_rfi_id BIGINT NOT NULL,
  name VARCHAR(255),
  radius FLOAT,
  radius_unit VARCHAR(2),
  target_type VARCHAR(20),
  coordinates VARCHAR(255)
);