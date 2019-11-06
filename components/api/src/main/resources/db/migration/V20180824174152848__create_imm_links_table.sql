CREATE TABLE imm_links (
  parent_object_id VARCHAR(100) NOT NULL,
  linked_object_id VARCHAR(100) NOT NULL,
  originating_system VARCHAR(100) NOT NULL,
  PRIMARY KEY (parent_object_id, linked_object_id, originating_system)
)