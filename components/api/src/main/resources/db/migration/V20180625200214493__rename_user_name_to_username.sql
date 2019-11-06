ALTER TABLE comments CHANGE user_name username VARCHAR(100);
ALTER TABLE rfis CHANGE user_name username VARCHAR(100);
ALTER TABLE users CHANGE user_name username VARCHAR(100);
ALTER TABLE rfi_service_events CHANGE user_name username VARCHAR(100);