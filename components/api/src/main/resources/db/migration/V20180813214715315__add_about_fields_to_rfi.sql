ALTER TABLE imm_rfis
  ADD COLUMN requirement_type_id INT,
  ADD COLUMN sub_workflow_id INT,
  ADD COLUMN classification_id INT,
  ADD COLUMN custom_classification VARCHAR(100),
  ADD COLUMN caveat_id INT,
  ADD COLUMN submitting_org_id INT,
  ADD COLUMN poc_signature_block TEXT,
  ADD COLUMN nipf_code_id INT,
  ADD COLUMN commander_pir_id INT,
  ADD COLUMN centcom_isr_role_id INT,
  ADD COLUMN operation VARCHAR(100)