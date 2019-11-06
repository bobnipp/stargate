class ImmRfi < ActiveRecord::Base
  validates :title, presence: true
  validates :country, presence: true
  has_one :target

  def self.make_rfi(options = {})
    attrs = {
      title: '',
      country: '',
      coordinates: '',
      city: '',
      region: '',
      nai: '',
      status: '',
      priority: '',
      justification: '',
      prev_research: '',
      custom_classification: '',
      poc: '',
      operation: '',
      collection_start_date: '',
      collection_end_date: '',
      collection_type: '',
      collection_term: '',
      assigned_team_id: '',
      assignee: '',
      collection_guidance: '',
      eeis: '["","",""]',
    }.merge(options)

    create!(attrs)
  end
end
