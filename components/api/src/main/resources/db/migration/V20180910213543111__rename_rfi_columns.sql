ALTER TABLE imm_rfis
  CHANGE description justification TEXT,
  CHANGE poc_signature_block poc TEXT,
  CHANGE commander_pir_id pir_name_id INT(11),
  CHANGE objective prev_research TEXT,
  CHANGE province region VARCHAR(100);