CREATE TABLE rfi_activities (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  rfi_id BIGINT NOT NULL,
  text TEXT,
  username VARCHAR(100),
  timestamp VARCHAR(100),
  FOREIGN KEY (rfi_id) REFERENCES imm_rfis(id)
);