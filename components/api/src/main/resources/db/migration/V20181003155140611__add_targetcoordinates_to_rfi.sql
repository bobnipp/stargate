ALTER TABLE imm_rfis
    DROP COLUMN latitude,
    DROP COLUMN longitude,
    ADD COLUMN target_coordinates VARCHAR(255);