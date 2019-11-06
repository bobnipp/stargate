require 'jira-ruby'

def setup_projects_and_fields
  init_jira_client
  create_projects
  create_custom_fields
end

def init_jira_client
  options = {
    username: 'pivotal.stargate',
    password: 'stargate',
    site: 'https://stargate-jira.cfapps.io',
    context_path: '',
    auth_type: :basic
  }

  $client = JIRA::Client.new(options)
end

def create_projects
  projects.each do |project|
    if project_exists project[:key]
      puts "Project #{project[:name]} already exists!"
    else
      create_project project[:key], project[:name]
    end
  end
  puts "Done creating new projects...\n\n"
end

def projects
  [
    {key: 'STAR', name: 'stargate-pipeline'},
    {key: 'SAND', name: 'product-sandbox'},
    {key: 'JEN', name: 'jenkins'},
    {key: 'TELL', name: 'teller'},
    {key: 'XAN', name: 'xanthia'},
    {key: 'BUT', name: 'butcher'},
    {key: 'QUAY', name: 'quay'},
    {key: 'MISC1', name: 'miscellaneous1'},
    {key: 'MISC2', name: 'miscellaneous2'},
  ]
end

def project_exists(key)
  !$client.Project.all
    .find {|project| project.key == key}
    .nil?
end

def create_project(key, name)
  project_config = {
    key: key,
    name: name,
    projectTypeKey: 'business',
    projectTemplateKey: 'com.atlassian.jira-core-project-templates:jira-core-process-management',
    lead: 'pivotal.stargate'
  }

  project = $client.Project.build
  saved = project.save project_config

  raise Exception, "Project not created! Error: #{project.errorMessages}" unless saved
  puts "Project #{name} created!"
end

def create_custom_fields
  custom_fields.each do |field|
    if field_already_exists field[:name]
      puts "Custom field #{field[:name]} already exists!"
    else
      create_custom_field field[:name], field[:type]
    end
  end
  puts "Done creating custom fields...\n\n"
end

def custom_fields
  [
    {name: 'Country', type: :text},
    {name: 'Latitude', type: :number},
    {name: 'Longitude', type: :number}
  ]
end

def field_already_exists(name)
  !$client.Field.all
    .find {|field| field.name == name}
    .nil?
end

def create_custom_field(name, type)
  field_config = {
    name: name,
    searcherKey: searcher_keys[type],
    type: field_types[type]
  }

  field = $client.Field.build
  saved = field.save field_config

  raise Exception, "Custom field not saved! Error #{field.errorMessages}" unless saved
  puts "Custom field #{name} created!"
end

def field_types
  {
    text: 'com.atlassian.jira.plugin.system.customfieldtypes:textfield',
    number: 'com.atlassian.jira.plugin.system.customfieldtypes:float'
  }
end

def searcher_keys
  {
    text: 'com.atlassian.jira.plugin.system.customfieldtypes:textsearcher',
    number: 'com.atlassian.jira.plugin.system.customfieldtypes:exactnumber'
  }
end
