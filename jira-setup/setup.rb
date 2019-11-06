require 'capybara'
require 'selenium/webdriver'

require_relative 'ui_helpers.rb'
require_relative 'api_helpers.rb'

initialize_jira
setup_projects_and_fields
add_custom_fields_to_projects
# add_custom_fields_to_projects_testing
