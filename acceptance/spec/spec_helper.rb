require 'bundler/setup'
require 'capybara/rspec'
require 'selenium/webdriver'
require 'require_all'
require 'active_record'
require 'database_cleaner'
require 'capybara-screenshot/rspec'
require 'active_support/testing/time_helpers'
require 'clipboard'
require 'chromedriver/helper'

Bundler.require :default

require_rel 'utils'
require_rel 'models'

RSpec.configure do |config|

  driver = Chromedriver::Helper.new
  driver.update('2.35')

  def db_configuration
    db_configuration_file = File.join(File.expand_path('..', __FILE__), '..', 'db', 'config.yml')
    YAML.load(File.read(db_configuration_file))
  end

  ActiveRecord::Base.establish_connection(db_configuration["test"])

  config.filter_run_when_matching :focus
  config.default_formatter = 'doc'
  config.order = :random
  config.example_status_persistence_file_path = 'examples.txt'
  config.include ActiveSupport::Testing::TimeHelpers

  DatabaseCleaner.strategy = :truncation, {:except => %w[flyway_schema_history]}

  config.before :each do
    DatabaseCleaner.clean
  end

  config.before(:each, js: true) do
    Capybara.page.current_window.resize_to(2400, 2000)
  end

  config.after :each do
    DatabaseCleaner.clean
  end

  Capybara.register_driver :headless_chrome do |app|
    if ENV['RUN_MAP_TESTS']
      capabilities = Selenium::WebDriver::Remote::Capabilities.chrome(
          chromeOptions: {args: %w(disable-gpu disable-extensions no-sandbox)}
      )
    else
      capabilities = Selenium::WebDriver::Remote::Capabilities.chrome(
          chromeOptions: {args: %w(headless disable-gpu disable-extensions no-sandbox)}
      )
    end

    Capybara::Selenium::Driver.new app, browser: :chrome, desired_capabilities: capabilities
  end

  Capybara.javascript_driver = :headless_chrome
  Capybara.app_host = 'http://localhost:8081'

  Capybara::Screenshot.register_driver(:headless_chrome) do |driver, path|
    driver.browser.save_screenshot(path)
  end
  Capybara::Screenshot.prune_strategy = :keep_last_run

  StargateUi.new.buildWithoutMap
  PrismMock.instance.run
  PrismAdapter.new.run
  StargateApi.new.run

  #populate the cache
  response = HTTParty.post('http://localhost:8082/cache')
  fail "Building the cache did not succeed." if response.code != 200
end

def login(username: 'user1', password: 'user1')
  return unless page.has_css?('input[name="username"]')
  fill_in 'username', with: username
  fill_in 'password', with: password
  click_button 'Sign in'
  click_button 'Authorize' if page.has_css?('button#authorize')
end

def click_tab(label)
  click_on label
end
