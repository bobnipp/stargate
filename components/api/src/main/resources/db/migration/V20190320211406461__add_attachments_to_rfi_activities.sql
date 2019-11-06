ALTER TABLE rfi_activities ADD COLUMN attachments JSON;

UPDATE rfi_activities SET attachments = JSON_ARRAY();

ALTER TABLE rfi_activities MODIFY attachments JSON NOT NULL;