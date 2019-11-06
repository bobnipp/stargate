CREATE VIEW `vw_rfi_service_events` AS
  SELECT
    hex(rfi_id) as rfi_id,
    version,
    event_type,
    event_data,
    user_name,
    occurred_at
  FROM rfi_service_events;