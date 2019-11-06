ALTER TABLE imm_rfis
    ADD COLUMN collection_type VARCHAR(100),
    ADD COLUMN collection_term VARCHAR(100),
    ADD COLUMN assigned_team_id INT,
    ADD COLUMN assignee VARCHAR(100),
    ADD COLUMN collection_guidance VARCHAR(100);