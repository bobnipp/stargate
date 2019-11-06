require_relative '../spec_helper'
require_relative 'common_actions'

describe 'health-check', type: :feature, js: true do

  it 'displays health check component list' do
    load_requests_tab
    start_redis_server
    sleep 5

    find('[data-aid=components-filter-button]').click
    within '[data-aid=filter-component]' do
      expect(page).to have_content('check_circle')
      expect(page).to have_content('minutes old')
    end
  end

  it 'displays global health indicator when a system is down' do
    load_requests_tab

    find('[data-aid=components-filter-button]').click
    within '[data-aid=filter-component]' do
      expect(page).to_not have_content('warning')
    end
    find('[data-aid=components-filter-button]').click

    stop_redis_server
    sleep 10

    find('[data-aid=components-filter-button]').click
    within '[data-aid=filter-component]' do
      expect(page).to have_content('warning')
      expect(page).not_to have_content('minutes old')
    end

    start_redis_server
    sleep 5
  end

  def start_redis_server
    if `uname`.include? 'Darwin'
      `brew services start redis`
    else
      `redis-server /etc/redis/redis.conf`
    end
  end

  def stop_redis_server
    if `uname`.include? 'Darwin'
      `brew services stop redis`
    else
      `redis-cli -a password shutdown`
    end
  end
end