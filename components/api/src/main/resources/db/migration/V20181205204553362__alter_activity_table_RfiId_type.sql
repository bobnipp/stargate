ALTER TABLE rfi_activities DROP FOREIGN KEY rfi_activities_ibfk_1;
ALTER TABLE rfi_activities CHANGE rfi_id rfi_id VARCHAR(100);
