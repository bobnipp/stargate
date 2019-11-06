def verify_refresh_verify
  yield
  load_requests_tab
  yield
end

def load_requests_tab
  visit '/'
  login
  click_tab 'view_list'
end

def load_map_tab
  visit '/'
  login
end
