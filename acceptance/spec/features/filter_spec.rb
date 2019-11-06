require_relative '../spec_helper'
require_relative 'common_actions'

describe 'filter bar', type: :feature, js: true do

  it 'displays' do
    visit '/'
    login

    expect(page).to have_selector '.filter-bar'

  end

  before do
    rfi = ImmRfi.create!(title: "Lets do it", country: 'US', status: 'To Do', coordinates: '39.7576663 -105.009217', priority: 'Low', justification: 'Search Word 1', nai: 'Search Word 2')
    ImmRfi.create!(title: "Lets do this too", country: 'CA', status: 'To Do', coordinates: '39.7501637, -104.9511417', priority: 'Routine', region: 'Search Word 1', prev_research: 'Search Word 2')
    ImmRfi.create!(title: "Something", country: 'Search Word 1', status: 'Working', coordinates: '39.7363236, -105.0196524', priority: 'Immediate', poc: 'Search Word 2')
    ImmRfi.create!(title: "ACC Test IMM Record", country: 'UK', status: 'Working', coordinates: '39.7363236, -105.0196524', priority: 'Immediate', city: 'Search Word 1', operation: 'Search Word 2')
    ImmRfi.create!(title: "Dont Show up", country: 'UK', status: 'Working', coordinates: '39.7363236, -105.0196524', priority: 'Immediate')

    Target.create!(imm_rfi_id: rfi.id, name: 'My Target', target_type: 'POINT', radius: "#{Random.rand.round(6)}", radius_unit: 'KM', coordinates: '30 -90')
    Sensor.create!(imm_rfi_id: rfi.id, sensor: '', sensor_type: 'EO', mode: 'mode', required_quality: 1, desired_quality: 2)
  end

  it 'filters map and list views by system' do
    load_requests_tab

    # INITIAL DATA PRESENT
    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(5)
    end

    within '[data-aid=requirements-list]' do
      nom_items = all('.list-table_item')
      expect(nom_items.size).to eq(2)
    end

    expect(page).not_to have_css('[data-aid=filter-component]')

    # APPLY SYSTEM FILTER: IMM
    find('[data-aid=components-filter-button]').click
    all('[data-aid=filter__checkbox]')[0].click
    expect(find('[data-aid=components-filter-button]')).to have_text('COMPONENTS')
    find('.filter-apply').click

    expect(find('[data-aid=components-filter-button]')).to have_text('IMM RFIS')

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(5)
    end

    within '[data-aid=requirements-list]' do
      nom_items = all('.list-table_item')
      expect(nom_items.size).to eq(0)
    end

    # VIEW MAP PAGE AND SEE SAME FILTERS APPLIED
    click_tab 'map'
    expect(find('[data-aid=components-filter-button]')).to have_text('IMM RFIS')

    within '[data-aid=sidebar-map-list]' do
      map_list_items = all('.list-table_item')
      expect(map_list_items.size).to eq(5)
    end

    # TOGGLE FILTER, APPLY SYSTEM FILTER: PRISM
    find('[data-aid=components-filter-button]').click
    all('[data-aid=filter__checkbox]')[0].click
    all('[data-aid=filter__checkbox]')[1].click
    expect(find('[data-aid=components-filter-button]')).to have_text('IMM RFIS')
    find('.filter-apply').click
    expect(find('[data-aid=components-filter-button]')).to have_text('PRISM NOMINATIONS')

    within '[data-aid=sidebar-map-list]' do
      map_list_items = all('.list-table_item')
      expect(map_list_items.size).to eq(2)
    end

    # VIEW LIST PAGE AND SEE SAME FILTERS APPLIED
    click_tab 'view_list'

    within '[data-aid=requirements-list]' do
      nom_items = all('.list-table_item')
      expect(nom_items.size).to eq(2)
    end

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(0)
    end

    # TOGGLE FILTER, APPLY SYSTEM FILTER: PRISM & IMM
    find('[data-aid=components-filter-button]').click
    all('[data-aid=filter__checkbox]')[0].click
    expect(find('[data-aid=components-filter-button]')).to have_text('PRISM NOMINATIONS')
    find('.filter-apply').click
    expect(find('[data-aid=components-filter-button]')).to have_text('COMPONENTS • 2')

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(5)
    end

    within '[data-aid=requirements-list]' do
      nom_items = all('.list-table_item')
      expect(nom_items.size).to eq(2)
    end

    # VISIT MAP PAGE, SEE SAME FILTERS APPLIED
    click_tab 'map'
    within '[data-aid=sidebar-map-list]' do
      map_list_items = all('.list-table_item')
      expect(map_list_items.size).to eq(7)
    end

    # CLEAR FILTERS IN FILTER DROPDOWN
    find('[data-aid=components-filter-button]').click
    find('.filter-clear').click

    within '[data-aid=sidebar-map-list]' do
      map_list_items = all('.list-table_item')
      expect(map_list_items.size).to eq(7)
    end

    # VISIT LIST VIEW, NO FILTERS APPLY
    click_tab 'view_list'
    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(5)
    end

    within '[data-aid=requirements-list]' do
      nom_items = all('.list-table_item')
      expect(nom_items.size).to eq(2)
    end


    find('[data-aid=components-filter-button]').click
    within '[data-aid=filter-component]' do
      expect(page).to have_field('option-0', visible: false, checked: false)
      expect(page).to have_field('option-1', visible: false, checked: false)
      expect(page).not_to have_css('.filter-clear')
    end
  end

  it 'displays and filters by status' do
    load_requests_tab

    # SET UP THE PAGE, ALL RFI AND PRISM ARE LISTED
    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(5)
    end
    within '[data-aid=requirements-list]' do
      requirements = all('.list-table_item')
      expect(requirements.size).to eq(2)
    end

    # APPLY A FILTER TO RFI ONLY
    find('[data-aid=status-filter-button]').click
    within '[data-aid=filter-component]' do
      all('[data-aid=filter__checkbox]')[0].click
      find('.filter-apply').click
    end

    # SEE CHANGE IN RFI LIST, PRISM IS CLEARED
    expect(find('[data-aid=status-filter-button]')).to have_text('TO DO')
    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(2)
    end

    within '[data-aid=requirements-list]' do
      requirements = all('.list-table_item')
      expect(requirements.size).to eq(0)
    end

    find('[data-aid=status-filter-button]').click
    all('[data-aid=filter__checkbox]')[1].click
    find('.filter-apply').click
    expect(find('[data-aid=status-filter-button]')).to have_text('STATUS • 2')

    find('[data-aid=status-filter-button]').click
    find('.filter-clear').click
    within '[data-aid=filter-component]' do
      expect(page).to have_field('option-46', visible: false, checked: false)
      expect(page).to have_field('option-47', visible: false, checked: false)
      expect(page).not_to have_css('.filter-clear')
    end
  end

  it 'displays and filters records by priority' do
    load_requests_tab

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(5)
    end

    expect(page).not_to have_css('[data-aid=filter-component]')

    find('[data-aid=priority-filter-button]').click
    within '[data-aid=filter-component]' do
      all('[data-aid=filter__checkbox]')[0].click

      find('.filter-apply').click
    end

    expect(find('[data-aid=priority-filter-button]')).to have_text('LOW')

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(1)
    end

    find('[data-aid=priority-filter-button]').click
    all('[data-aid=filter__checkbox]')[1].click
    find('.filter-apply').click
    expect(find('[data-aid=priority-filter-button]')).to have_text('PRIORITY • 2')

    find('[data-aid=priority-filter-button]').click
    find('.filter-clear').click
    within '[data-aid=filter-component]' do
      expect(page).to have_field('option-50', visible: false, checked: false)
      expect(page).to have_field('option-51', visible: false, checked: false)
      expect(page).not_to have_css('.filter-clear')
    end

    find('.side-nav').click
    expect(page).not_to have_css('.filter__selected')
  end

  it 'displays and filters records by target type and retains filter after refreshing' do
    load_requests_tab

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(5)
    end

    expect(page).not_to have_css('[data-aid=filter-component]')

    find('[data-aid=target-type-filter-button]').click
    within '[data-aid=filter-component]' do
      all('[data-aid=filter__checkbox]')[0].click

      find('.filter-apply').click
    end

    expect(find('[data-aid=target-type-filter-button]')).to have_text('POINT')

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(1)
    end

    find('[data-aid=target-type-filter-button]').click
    all('[data-aid=filter__checkbox]')[1].click
    find('.filter-apply').click
    expect(find('[data-aid=target-type-filter-button]')).to have_text('TARGET TYPE • 2')

    visit '/'
    click_tab 'view_list'

    expect(find('[data-aid=target-type-filter-button]')).to have_text('TARGET TYPE • 2')

    find('[data-aid=target-type-filter-button]').click
    find('.filter-clear').click
    within '[data-aid=filter-component]' do
      expect(page).to have_field('option-0', visible: false, checked: false)
      expect(page).to have_field('option-1', visible: false, checked: false)
      expect(page).not_to have_css('.filter-clear')
    end

    find('.side-nav').click
    expect(page).not_to have_css('.filter__selected')
  end

  it 'displays and filters records by sensor type' do
    load_requests_tab

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(5)
    end

    expect(page).not_to have_css('[data-aid=filter-component]')

    find('[data-aid=sensor-type-filter-button]').click
    within '[data-aid=filter-component]' do
      all('[data-aid=filter__checkbox]')[0].click

      find('.filter-apply').click
    end

    expect(find('[data-aid=sensor-type-filter-button]')).to have_text('EO')

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(1)
    end

    find('[data-aid=sensor-type-filter-button]').click
    all('[data-aid=filter__checkbox]')[1].click
    find('.filter-apply').click
    expect(find('[data-aid=sensor-type-filter-button]')).to have_text('SENSOR TYPE • 2')

    find('[data-aid=sensor-type-filter-button]').click
    find('.filter-clear').click
    within '[data-aid=filter-component]' do
      expect(page).to have_field('option-0', visible: false, checked: false)
      expect(page).to have_field('option-1', visible: false, checked: false)
      expect(page).not_to have_css('.filter-clear')
    end

    find('.side-nav').click
    expect(page).not_to have_css('.filter__selected')
  end

  it 'clears all filters' do
    load_requests_tab

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(5)
    end

    find('[data-aid=status-filter-button]').click
    within '[data-aid=filter-component]' do
      all('[data-aid=filter__checkbox]')[0].click
      find('.filter-apply').click
    end

    find('[data-aid=priority-filter-button]').click
    within '[data-aid=filter-component]' do
      all('[data-aid=filter__checkbox]')[0].click
      find('.filter-apply').click
    end

    find('[data-aid=components-filter-button]').click
    within '[data-aid=filter-component]' do
      all('[data-aid=filter__checkbox]')[0].click
      find('.filter-apply').click
    end

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(1)
    end

    find('#search-input').send_keys 'LAST'

    find('.filter-clear-all').click

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(5)
    end

    expect(find('#search-input')).to have_text('')

    visit '/'
    click_tab 'view_list'

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(5)
    end
  end

  it 'clicking outside the filter resets the selected filters to the last state' do
    load_requests_tab

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(5)
    end

    find('[data-aid=status-filter-button]').click
    within '[data-aid=filter-component]' do
      all('[data-aid=filter__checkbox]')[0].click
      find('.filter-apply').click
    end

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(2)
    end

    #select but don't apply the 'Working' status
    find('[data-aid=status-filter-button]').click
    within '[data-aid=filter-component]' do
      all('[data-aid=filter__checkbox]')[1].click
    end

    find('.side-nav').click

    #list is unchanged
    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(2)
    end

    expect(page).not_to have_selector('.status-filter-popup')

    find('[data-aid=status-filter-button]').click
    within '[data-aid=filter-component]' do
      expect(page).to have_field('option-46', visible: false, checked: true)
      expect(page).to have_field('option-47', visible: false, checked: false)
      expect(page).to have_field('option-48', visible: false, checked: false)
      expect(page).to have_field('option-49', visible: false, checked: false)
      expect(page).to have_css('.filter-clear')
    end
  end

  it 'clicking outside the filter resets the system filter to a non-checked state' do
    load_requests_tab

    find('[data-aid=components-filter-button]').click
    within '[data-aid=filter-component]' do
      all('[data-aid=filter__checkbox]')[0].click
      all('[data-aid=filter__checkbox]')[1].click
    end

    find('.side-nav').click

    find('[data-aid=components-filter-button]').click
    within '[data-aid=filter-component]' do
      expect(page).to have_field('option-0', visible: false, checked: false)
      expect(page).to have_field('option-1', visible: false, checked: false)
    end
  end

  it 'filter menu renders options in proper case' do
    load_requests_tab

    find('[data-aid=status-filter-button]').click
    within '[data-aid=filter-component]' do
      expect(page).to have_text('To Do')
      expect(page).to have_text('Working')
      expect(page).to have_text('Active')
      expect(page).to have_text('Closed')
      expect(page).to have_text('Approve')
      expect(page).to have_text('Hold')
      expect(page).to have_text('Rework')
      expect(page).to have_text('Submitted')
      expect(page).to have_text('Vote')
      expect(page).to have_text('Working')
      expect(page).to have_text('Template')
      expect(page).to have_text('Forward')
      expect(page).to have_text('Send To NSRP')
      expect(page).to have_text('Send To RMS')
    end

    find('[data-aid=priority-filter-button]').click
    within '[data-aid=filter-component]' do
      expect(page).to have_text('Low')
      expect(page).to have_text('Routine')
      expect(page).to have_text('Immediate')
      expect(page).to have_text('Immediate')
      expect(page).to have_text('Priority')
      expect(page).to have_text('Routine')
    end

    find('[data-aid=components-filter-button]').click
    within '[data-aid=filter-component]' do
      expect(page).to have_text('RFI')
      expect(page).to have_text('NOM')
    end
  end

  it 'disables component-specific filters when the component is filtered' do
    load_requests_tab

    find('[data-aid=priority-filter-button]').click
    within '[data-aid=filter-component]' do
      expect(page).not_to have_css('.display-name_disabled')
    end

    find('[data-aid=components-filter-button]').click
    within '[data-aid=filter-component]' do
      all('[data-aid=filter__checkbox]')[0].click
      find('.filter-apply').click
    end

    find('[data-aid=priority-filter-button]').click
    within '[data-aid=filter-component]' do
      groups = all('.select-option-group')
      expect(groups[0]).not_to have_css('.display-name_disabled')
      expect(groups[1]).to have_css('.display-name_disabled')
    end

    find('[data-aid=components-filter-button]').click
    within '[data-aid=filter-component]' do
      all('[data-aid=filter__checkbox]')[0].click
      find('.filter-apply').click
    end

    find('[data-aid=priority-filter-button]').click
    within '[data-aid=filter-component]' do
      expect(page).not_to have_css('.display-name_disabled')
    end
  end

  describe 'search filter' do
    it 'filters prism records by search term' do
      load_requests_tab

      # SET UP THE PAGE, ALL RFI AND PRISM ARE SEEN
      within '[data-aid=rfi-list]' do
        rfi_items = all('.list-table_item')
        expect(rfi_items.size).to eq(5)
      end
      within '[data-aid=requirements-list]' do
        requirements = all('.list-table_item')
        expect(requirements.size).to eq(2)
      end

      find('#search-input').send_keys 'LAST'
      find('#search-button').click

      within '[data-aid=requirements-list]' do
        requirements = all('.list-table_item')
        expect(requirements.size).to eq(1)
      end

      visit '/'
      click_tab 'view_list'

      expect(find('#search-input').value).to eq 'LAST'

      within '[data-aid=requirements-list]' do
        requirements = all('.list-table_item')
        expect(requirements.size).to eq(1)
      end

    end

    it 'filters imm records by search term' do
      load_requests_tab

      # SET UP THE PAGE, ALL RFI AND PRISM ARE SEEN
      within '[data-aid=rfi-list]' do
        rfi_items = all('.list-table_item')
        expect(rfi_items.size).to eq(5)
      end
      within '[data-aid=requirements-list]' do
        requirements = all('.list-table_item')
        expect(requirements.size).to eq(2)
      end

      find('#search-input').send_keys 'do it'
      find('#search-button').click

      within '[data-aid=rfi-list]' do
        rfi_items = all('.list-table_item')
        expect(rfi_items.size).to eq(1)
      end
    end

    it 'simultaneously searches prism and imm records by search term' do
      load_requests_tab

      # SET UP THE PAGE, ALL RFI AND PRISM ARE SEEN
      within '[data-aid=rfi-list]' do
        rfi_items = all('.list-table_item')
        expect(rfi_items.size).to eq(5)
      end
      within '[data-aid=requirements-list]' do
        requirements = all('.list-table_item')
        expect(requirements.size).to eq(2)
      end

      find('#search-input').send_keys 'ACC'
      find('#search-button').click

      within '[data-aid=rfi-list]' do
        rfi_items = all('.list-table_item')
        expect(rfi_items.size).to eq(1)
      end

      find('.filter-clear-all').click

      find('#search-input').send_keys 'another'
      find('#search-button').click

      within '[data-aid=rfi-list]' do
        rfi_items = all('.list-table_item')
        expect(rfi_items.size).to eq(0)
      end

      within '[data-aid=requirements-list]' do
        requirements = all('.list-table_item')
        expect(requirements.size).to eq(1)
      end

    end

    it 'searches by keyword on enter' do
      load_requests_tab

      # SET UP THE PAGE, ALL RFI AND PRISM ARE SEEN
      within '[data-aid=rfi-list]' do
        rfi_items = all('.list-table_item')
        expect(rfi_items.size).to eq(5)
      end
      within '[data-aid=requirements-list]' do
        requirements = all('.list-table_item')
        expect(requirements.size).to eq(2)
      end

      find('#search-input').send_keys 'ACC'
      find('#search-input').send_keys(:enter)

      within '[data-aid=rfi-list]' do
        rfi_items = all('.list-table_item')
        expect(rfi_items.size).to eq(1)
      end
    end

    it 'searches by multiple fields' do
      load_requests_tab

      within '[data-aid=rfi-list]' do
        rfi_items = all('.list-table_item')
        expect(rfi_items.size).to eq(5)
      end
      within '[data-aid=requirements-list]' do
        requirements = all('.list-table_item')
        expect(requirements.size).to eq(2)
      end

      find('#search-input').send_keys 'Search Word 1'
      find('#search-button').click

      within '[data-aid=rfi-list]' do
        rfi_items = all('.list-table_item')
        expect(rfi_items.size).to eq(4)
      end

      visit '/'
      click_tab 'view_list'

      within '[data-aid=rfi-list]' do
        rfi_items = all('.list-table_item')
        expect(rfi_items.size).to eq(4)
      end

      fill_in 'search-input', with: ''
      find('#search-input').send_keys 'Search Word 2'
      find('#search-button').click

      within '[data-aid=rfi-list]' do
        rfi_items = all('.list-table_item')
        expect(rfi_items.size).to eq(4)
      end
    end
  end
end
