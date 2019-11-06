ALTER TABLE imm_rfis
  ADD COLUMN priority ENUM ('LOW', 'ROUTINE', 'IMMEDIATE'),
  ADD COLUMN status ENUM ('TO_DO', 'WORKING', 'ACTIVE', 'CLOSED'),
  Add COLUMN description TEXT,
  Add COLUMN objective TEXT