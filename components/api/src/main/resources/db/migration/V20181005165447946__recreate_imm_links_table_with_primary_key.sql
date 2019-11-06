DROP TABLE imm_links;
CREATE TABLE imm_links (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  parent_object_id VARCHAR(100) NOT NULL,
  linked_object_id VARCHAR(100) NOT NULL,
  originating_system VARCHAR(100) NOT NULL,
  CONSTRAINT UNIQUE (parent_object_id, linked_object_id, originating_system)
);