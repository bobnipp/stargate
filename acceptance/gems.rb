source 'https://rubygems.org'
ruby '2.5.1'

gem 'httparty'
gem 'bundler'
gem 'require_all'
gem 'activesupport'
gem 'colorize'
gem 'jira-ruby'

group :test do
  gem 'rspec'
  gem 'fuubar'
  gem 'chromedriver-helper'
  gem 'capybara'
  gem 'capybara-selenium'
  gem 'database_cleaner'
  gem 'launchy'

  gem 'activerecord'
  gem 'mysql2'
  gem 'capybara-screenshot'
  gem 'httparty'
  gem 'clipboard'
end
