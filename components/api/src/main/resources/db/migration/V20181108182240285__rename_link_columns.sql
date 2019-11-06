ALTER TABLE imm_links
  CHANGE parent_object_id record_1_id VARCHAR(100),
  CHANGE linked_object_id record_2_id VARCHAR(100),
  CHANGE originating_system record_1_system VARCHAR(100),
  ADD COLUMN record_2_system VARCHAR(100);

ALTER TABLE imm_links
  ADD CONSTRAINT UNIQUE (record_1_id, record_2_id, record_1_system, record_2_system);
--   DROP CONSTRAINT 'parent_object_id',  HOOOOWWW!?
