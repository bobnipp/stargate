def initialize_jira
  setup_driver
  visit_jira
  wait_for_startup
  set_it_up_for_me
  wait_until_ready
  puts "Done with initial Jira setup...\n\n"
end

def setup_driver
  Capybara.register_driver :headless_chrome do |app|
    capabilities = Selenium::WebDriver::Remote::Capabilities.chrome(
      chromeOptions: {args: %w(disable-extensions no-sandbox)}
    )

    Capybara::Selenium::Driver.new app,
                                   browser: :chrome,
                                   desired_capabilities: capabilities
  end

  $page = Capybara::Session.new(:headless_chrome)
end

def visit_jira
  $page.visit 'http://stargate-jira.cfapps.io'
end

def wait_for_startup
  wait_for_content 'JIRA setup'
end

def set_it_up_for_me
  login_with_google
  puts 'Logged in...'
  setup_license
  puts 'Configured license...'
  setup_admin_account
  puts 'Admin account setup...'
end

def login_with_google
  $page.click_button 'Continue to MyAtlassian'
  $page.click_button 'Log in with Google'
  $page.fill_in 'identifier', with: 'pivotal.stargate@gmail.com'
  $page.find('#identifierNext').click
  $page.fill_in 'password', with: ENV['PROJECT_PASSWORD']
  $page.find('#passwordNext').click
end

def setup_license
  sleep 3
  $page.click_button 'Generate License'
  sleep 3
  $page.click_button 'Yes'
end

def setup_admin_account
  $page.fill_in 'Email', with: 'pivotal.stargate@gmail.com'
  $page.fill_in 'Password', with: 'stargate'
  $page.fill_in 'Re-type password', with: 'stargate'
  $page.click_button 'Next'
end

def wait_until_ready
  wait_for_content 'JIRA is ready to go!'
end

def finish_first_time_screens
  $page.click_button "Let's get started"
  $page.click_button 'Continue'
  $page.click_button 'Next'
  puts 'Done with one-time screens...'
end

def wait_for_content(content, timeout: 120)
  timeout.times do
    break if $page.has_content? content
    sleep 1
  end
end

def add_custom_fields_to_projects
  finish_first_time_screens
  close_notifications
  navigate_to_custom_fields
  add_custom_fields_to_screens
  puts '*** All done! ***'
end

def add_custom_fields_to_projects_testing
  # A bunch of stuff is different if you continue from the previous step
  # vs starting a fresh page here. This method is to use independently
  # from starting up Jira and creating the projects / custom fields
  setup_driver
  visit_jira
  login
  navigate_to_custom_fields anticipate_login: true
  add_custom_fields_to_screens
end

def login
  $page.fill_in 'Username', with: 'pivotal.stargate'
  $page.fill_in 'Password', with: 'stargate'
  $page.click_button 'Log In'
end

def navigate_to_custom_fields(anticipate_login: false)
  $page.find('#system-admin-menu').click
  $page.within '.aui-dropdown2-section' do
    $page.click_link 'Issues'
  end
  login_again if anticipate_login
  $page.click_link 'Custom fields'
end

def login_again
  $page.fill_in 'Password', with: 'stargate'
  $page.click_button 'Confirm'
end

def close_notifications
  sleep 5
  $page.find('.icon-close').click
  $page.click_button 'Ok, got it'
  puts 'Closed annoying notifications that block the admin menu...'
end

def add_custom_fields_to_screens
  custom_fields_count = $page.all('#custom-fields tbody tr').size

  custom_fields_count.times do |index|
    add_custom_field_all_screens(index)
  end
end

def navigate_to_screen_configuration(index)
  row = $page.all('#custom-fields tbody tr')[index]
  $page.within row do
    $page.find('.aui-iconfont-configure').click
  end

  $page.within '.aui-dropdown2' do
    $page.click_link 'Screens'
  end
end

def add_custom_field_all_screens(index)
  navigate_to_screen_configuration(index)
  check_all_checkboxes
  $page.click_button 'Update'
  puts "Added custom field #{index} to all screens!"
end

def check_all_checkboxes
  $page.all('input[type=checkbox]').each do |checkbox|
    checkbox.set(true)
  end
end
