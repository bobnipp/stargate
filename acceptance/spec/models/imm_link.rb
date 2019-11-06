class ImmLink < ActiveRecord::Base
  validates :record_1_system, presence: true
  validates :record_1_id, presence: true
  validates :record_2_system, presence: true
  validates :record_2_id, presence: true
end