require_relative '../spec_helper'
require_relative 'common_actions'

describe 'lists bar', type: :feature, js: true do
  sidebar_animation_wait_time = 1

  it 'displays' do
    load_requests_tab

    expect(page).to have_selector '.lists-bar'

    within '.lists-bar' do
      listTitles = all('.list-title')
      expect(listTitles[0]).to have_content('IMM RFIs')
      expect(listTitles[1]).to have_content('PRISM NOMs')

      expect(page).to have_css('.add-list')
    end
  end

  it 'displays existing rfis in descending order of creation' do
    rfi1 = ImmRfi.create!(title: 'Let\'s do it',
                          justification: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s',
                          status: 'To Do',
                          coordinates: '39.7410 -104.9903',
                          country: 'US')
    rfi2 = ImmRfi.create!(title: 'Let\'s do this too',
                          justification: 'test',
                          status: 'Working',
                          coordinates: '39.7392 -104.9903',
                          country: 'US')
    rfi3 = ImmRfi.create!(title: 'Won\'t this be fun',
                          status: 'Closed',
                          coordinates: '39.7200 -104.9903',
                          country: 'US')

    load_requests_tab

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')

      expect(rfi_items.size).to eq(3)

      expect(rfi_items[0]).to have_content 'Won\'t this be fun'
      expect(rfi_items[0]).to have_content "RFI ID ##{rfi3.id}"
      expect(rfi_items[0]).to have_content 'Closed'

      expect(rfi_items[1]).to have_content 'Let\'s do this too'
      expect(rfi_items[1]).to have_content 'test'
      expect(rfi_items[1]).to have_content "RFI ID ##{rfi2.id}"
      expect(rfi_items[1]).to have_content 'Working'

      expect(rfi_items[2]).to have_content 'Let\'s do it'
      expect(rfi_items[2]).to have_content 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s'
      expect(rfi_items[2]).to have_content "RFI ID ##{rfi1.id}"
      expect(rfi_items[2]).to have_content 'To Do'
    end
  end

  describe 'create list component' do
    it 'closes by clicking cancel' do
      load_requests_tab
      # Open the create list component
      find('.add-list').click
      expect(page).to have_content('Create List')
      expect(page).to have_content('Lists are where you can organize and track requirements')

      expect(page).to have_button('CREATE LIST', disabled: true)
      fill_in 'Name', with: 'List One'
      expect(page).to have_button('CREATE LIST', disabled: false)

      # cancel by clicking 'cancel'
      find('#cancel').click
      expect(page).not_to have_content('Create List')
    end

    it 'adds a custom list and deletes it' do
      load_requests_tab
      # open create list component
      find('.add-list').click
      fill_in 'Name', with: 'List One'

      # create a new list
      find('#create').click
      expect(page).to have_css('.column-container')

      # see the list in the columns
      expect(page).to have_content('List One')

      # see the list in the lists bar, with default active css
      within '.lists-bar' do
        activeTitles = all('.list-title.active')
        expect(activeTitles.size).to eq(3)
        expect(activeTitles[2]).to have_content('List One')

        #deactivate 'List One'
        activeTitles[2].click
        expect(page).to have_css('.list-title.active', text: 'IMM RFIs')
        expect(page).to have_css('.list-title.active', text: 'PRISM NOMs')
        expect(page).not_to have_css('.list-title.active', text: 'List One')
      end

      # List one is no longer an open column
      customLists = all('.custom-list')
      expect(customLists.size).to eq(0)

      # open create list component
      find('.add-list').click
      fill_in 'Name', with: 'List Two'

      # create a new list
      find('#create').click
      expect(page).to have_css('.column-container')

      # that list appears, 'List One' is deactivated
      expect(page).to have_content('List Two')

      within '.lists-bar' do
        expect(page).to have_css('.list-title.active', text: 'IMM RFIs')
        expect(page).to have_css('.list-title.active', text: 'PRISM NOMs')
        expect(page).to have_css('.list-title.active', text: 'List Two')

        expect(page).not_to have_css('.list-title.active', text: 'List One')
        expect(page).to have_css('.list-title', text: 'List One')
      end

      customLists = all('.custom-list')
      expect(customLists.size).to eq(1)

      # delete List Two
      within customLists[0] do
        find('.more-menu').click
        find('.delete').click
      end
      expect(page).not_to have_content('List Two')
    end
  end

  it 'displays columns accordingly' do
    ImmRfi.create!(title: 'Let\'s do it',
                   justification: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s',
                   status: 'To Do',
                   coordinates: '39.7410 -104.9903',
                   country: 'US')
    load_requests_tab
    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      expect(rfi_items.size).to eq(1)
    end

    within '[data-aid=requirements-list]' do
      nom_items = all('.list-table_item')
      expect(nom_items.size).to eq(2)
    end

    within '.lists-bar' do
      expect(page).to have_css('.list-title.active', text: 'IMM RFIs')
      expect(page).to have_css('.list-title.active', text: 'PRISM NOMs')

      listTitles = all('.list-title')
      listTitles[0].click
      expect(page).not_to have_css('.list-title.active', text: 'IMM RFIs')
    end

    expect(page).not_to have_css('[data-aid=rfi-list]')

    within '[data-aid=requirements-list]' do
      nom_items = all('.list-table_item')
      expect(nom_items.size).to eq(2)
    end

    within '.lists-bar' do
      listTitles = all('.list-title')
      listTitles[1].click
    end

    expect(page).not_to have_css('[data-aid=rfi-list]')
    expect(page).not_to have_css('[data-aid=requirements-list]')

    # activate IMM RFIs
    within '.lists-bar' do
      listTitles = all('.list-title')
      listTitles[0].click
    end
    expect(page).to have_css('[data-aid=rfi-list]')

    # close IMM RFIs via 'x'
    find('.close-column').click
    expect(page).not_to have_css('[data-aid=rfi-list]')
  end

  it 'updates an open links list when a new link is added in the sidebar' do
    rfi1 = ImmRfi.create!(title: 'Let\'s do it',
                          justification: 'Lorem Ipsum is simply dummy text',
                          status: 'Working',
                          coordinates: '39.7410 -104.9903',
                          country: 'US',
                          priority: 'Routine',
                          prev_research: 'say never sometimes',
                          requirement_type_id: 2,
                          sub_workflow_id: 7,
                          classification_id: 12,
                          caveat_id: 17,
                          submitting_org_id: 22,
                          pir_name_id: 32,
                          nipf_code_id: 27,
                          centcom_isr_role_id: 37,
                          custom_classification: 'vault 101',
                          poc: 'The Institute',
                          operation: 'Blow up the world',
                          collection_start_date: '12 Aug 2018 00:00',
                          collection_end_date: '15 Nov 2018 00:00',
                          collection_type: 'collection type1',
                          collection_term: 'collection term1',
                          assigned_team_id: 42,
                          assignee: 'assignee1',
                          collection_guidance: 'collection guidance1',
                          eeis: '["eei data 1", "eei data 2", "eei data 3", "eei data 4", "eei data 5"]'
    )
    rfi2 = ImmRfi.create!(title: 'Let\'s do this too',
                          justification: 'test',
                          status: 'Working',
                          coordinates: '39.7392 -104.9903',
                          country: 'US')

    ImmLink.create!(record_1_system: 'IMM', record_1_id: rfi1.id, record_2_id: 'ACC 01 EO-IR-SAR RECON 1:2 20160921', record_2_system: 'PRISM')

    load_requests_tab

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')

      within rfi_items[1] do
        page.find('.open-link-list').click
      end
    end

    within '.links-list' do
      records = all('.list-table_item')
      expect(records.size).to eq(2)
    end

    within '[data-aid=rfi-list]' do
      find('.list-table_item', text: rfi1.title).click
    end

    within '.sidebar' do
      sleep sidebar_animation_wait_time
      find('[data-aid=add-button-text]', text: 'Add a Link').click
      within '#link-info' do
        select 'IMM', from: 'Originating System'
        fill_in 'ID', with: rfi2.id
        click_button 'Add'
      end

      click_on 'Cancel'
    end

    within '.links-list' do
      expect(page).to have_content(rfi2.title)
    end

  end

  it 'deletes an imm record that has targets, links, and is a part of a list' do
    rfi = ImmRfi.create!(title: 'Let\'s do it',
                         justification: 'Lorem Ipsum is simply dummy text',
                         status: 'To Do',
                         coordinates: '39.7410 -104.9903',
                         country: 'US',
                         priority: 'Routine',
                         prev_research: 'say never sometimes',
                         requirement_type_id: 2,
                         sub_workflow_id: 7,
                         classification_id: 12,
                         caveat_id: 17,
                         submitting_org_id: 22,
                         pir_name_id: 32,
                         nipf_code_id: 27,
                         centcom_isr_role_id: 37,
                         custom_classification: 'vault 101',
                         poc: 'The Institute',
                         operation: 'Blow up the world',
                         collection_start_date: '12 Aug 2018 00:00',
                         collection_end_date: '15 Nov 2018 00:00',
                         collection_type: 'collection type1',
                         collection_term: 'collection term1',
                         assigned_team_id: 42,
                         assignee: 'assignee1',
                         collection_guidance: 'collection guidance1',
                         eeis: '["eei data 1", "eei data 2", "eei data 3", "eei data 4", "eei data 5"]')

    Target.create!(imm_rfi_id: rfi.id, name: 'My Target', target_type: 'POINT', radius: "#{Random.rand.round(6)}", radius_unit: 'KM', coordinates: '30 -90')
    List.create!(name: 'Test List', records: [])
    ImmLink.create!(record_1_system: 'IMM', record_1_id: rfi.id, record_2_id: 'ACC 01 EO-IR-SAR RECON 1:2 20160921', record_2_system: 'PRISM')

    load_requests_tab

    find('#add-button').click
    within '.sidebar' do
      sleep sidebar_animation_wait_time
      find('.sidebar_action--menu').click
      expect(page).not_to have_css('.delete-record')
      click_on 'Cancel'
    end

    within '[data-aid=rfi-list]' do
      find('.list-table_item-action--menu').click
      expect(page).to have_css('.delete-record')
    end

    within '[data-aid=requirements-list]' do
      within('.list-table_item', text: 'ACC 01 EO-IR-SAR RECON 1:2 20160921') do
        find('.list-table_item-action--menu').click
        expect(page).not_to have_css('.delete-record')
      end
    end

    find('.list-table_item', text: 'ACC 01 EO-IR-SAR RECON 1:2 20160921').click
    within '.sidebar' do
      sleep sidebar_animation_wait_time
      find('.sidebar_action--menu').click
      expect(page).not_to have_css('.delete-record')
      find('a', text: 'Add to List').click
      find('a', text: 'Test List').click
      within '.existing-links' do
        expect(page).to have_content 'IMM'
        expect(page).to have_content "##{rfi.id}"
      end
    end

    find('.list-table_item', text: rfi.title).click
    within '.sidebar'do
      sleep sidebar_animation_wait_time
      find('.sidebar_action--menu', wait: 3).click
      find('a', text: 'Add to List').click
      find('a', text: 'Test List').click
    end

    expect(page).to have_content('Test List')
    within '.lists-bar' do
      listTitles = all('.list-title')
      listTitles[2].click
    end

    within '[data-aid="Test List"]' do
      expect(page).to have_content('ACC 01 EO-IR-SAR RECON 1:2 20160921')
      expect(page).to have_content('Lorem Ipsum is simply dummy text')
    end

    within '[data-aid=rfi-list]' do
      rfi_items = all('.list-table_item')
      within rfi_items[0] do
        page.find('.open-link-list').click
        expect(page).to have_css('.open-link-list_active')
      end
      find('.list-table_item', text: rfi.title).click
    end

    within '.sidebar' do
      sleep sidebar_animation_wait_time
      find('.sidebar_action--menu').click
      expect(page).to have_css('.delete-record')
      find('a', text: 'Delete').click
    end

    expect(page).to have_content('Are you sure you want to delete this record?')
    click_on 'NO, CANCEL'

    within '.sidebar' do
      sleep sidebar_animation_wait_time
      find('a', text: 'Delete').click
    end

    click_on 'YES, DELETE'
    expect(page).to have_content("#{rfi.title} has been deleted")

    expect(page).not_to have_css('.sidebar')
    within '[data-aid="Test List"]' do
      expect(page).to have_content('ACC 01 EO-IR-SAR RECON 1:2 20160921')
      expect(page).not_to have_content('Lorem Ipsum is simply dummy text')
    end

    expect(page).not_to have_css('.open-link-list_active')

    within '[data-aid=rfi-list]' do
      expect(page).not_to have_content('Lorem Ipsum is simply dummy text')
    end

    within('[data-aid=requirements-list]') do
      find('.list-table_item', text: 'ACC 01 EO-IR-SAR RECON 1:2 20160921').click
    end

    within '.sidebar' do
      expect(page).not_to have_css('.existing-links')
    end

  end

  it 'adds and removes a record from a list' do
    rfi = ImmRfi.create!(title: 'Let\'s do it',
                         justification: 'Lorem Ipsum is simply dummy text',
                         status: 'To Do',
                         coordinates: '39.7410 -104.9903',
                         country: 'US',
                         priority: 'Routine',
                         prev_research: 'say never sometimes',
                         requirement_type_id: 2,
                         sub_workflow_id: 7,
                         classification_id: 12,
                         caveat_id: 17,
                         submitting_org_id: 22,
                         pir_name_id: 32,
                         nipf_code_id: 27,
                         centcom_isr_role_id: 37,
                         custom_classification: 'vault 101',
                         poc: 'The Institute',
                         operation: 'Blow up the world',
                         collection_start_date: '12 Aug 2018 00:00',
                         collection_end_date: '15 Nov 2018 00:00',
                         collection_type: 'collection type1',
                         collection_term: 'collection term1',
                         assigned_team_id: 42,
                         assignee: 'assignee1',
                         collection_guidance: 'collection guidance1',
                         eeis: '["eei data 1", "eei data 2", "eei data 3", "eei data 4", "eei data 5"]')

    List.create!(name: 'Test List', records: [])
    List.create!(name: '2nd List', records: [])

    load_requests_tab

    find('.list-table_item', text: rfi.title).click
    within '.sidebar' do
      sleep sidebar_animation_wait_time
      find('.sidebar_action--menu').click
      find('a', text: 'Add to List').click
      find('a', text: 'Test List').click

      find('.sidebar_action--menu').click
      find('a', text: 'Add to List').click
      find('a', text: '2nd List').click

      find('.sidebar_action--menu').click
      expect(page).not_to have_css('.remove-record')
    end

    expect(page).to have_content('Added to 2nd List')

    find('.list-table_item', text: rfi.title).click
    within '.sidebar' do
      sleep sidebar_animation_wait_time
      find('.sidebar_action--menu', wait: 3).click
      find('a', text: 'Add to List').click
      find('a', text: '2nd List').click
    end

    expect(page).to have_content('Already exists in 2nd List')

    within '[data-aid=requirements-list]' do
      within('.list-table_item', text: 'ACC 01 EO-IR-SAR RECON 1:2 20160921') do
        find('.list-table_item-action--menu').click
        expect(page).not_to have_css('.remove-record')
      end
    end

    within '[data-aid=rfi-list]' do
      within('.list-table_item', text: rfi.title) do
        find('.list-table_item-action--menu').click
        expect(page).not_to have_css('.remove-record')
      end
    end

    within '.lists-bar' do
      listTitles = all('.list-title')
      listTitles[2].click
      listTitles[3].click
    end

    within '[data-aid="Test List"]' do
      find('.list-table_item-action--menu').click
      find('.remove-record').click
    end

    within '[data-aid="Test List"]' do
      expect(page).not_to have_content(rfi.justification)
    end

    within '[data-aid="2nd List"]' do
      expect(page).to have_content(rfi.justification)
    end

  end

  it 'opens and closes' do
    load_requests_tab

    expect(page).to have_css('.arrow-icon')
    find('.arrow-icon').click
    find('.arrow-icon_closed').click
    expect(page).to have_css('.arrow-icon')
  end

  it 'refreshes the list' do
    load_requests_tab

    rfiForRefresh = ImmRfi.create!(title: 'Won\'t this be fun',
                                   status: 'Closed',
                                   coordinates: '39.7200 -104.9903',
                                   country: 'US')

    within '[data-aid=rfi-list]' do
      expect(page).not_to have_content(rfiForRefresh.title)
      find('.refresh').click
      expect(page).to have_content(rfiForRefresh.title)
    end
  end

  it 'adds to and reorders a custom list via drag and drop' do
    ImmRfi.create!(title: 'Test 1',
                          status: 'Closed',
                          priority: 'Routine',
                          justification: 'Lorem Ipsum is simply dummy text',
                          coordinates: '39.7200 -104.9903',
                          country: 'US',
                          eeis: '["eei data 1", "eei data 2", "eei data 3", "eei data 4", "eei data 5"]')
    rfi2 = ImmRfi.create!(title: 'Test 2',
                          status: 'Closed',
                          priority: 'Routine',
                          justification: 'Lorem Ipsum is simply dummy text',
                          coordinates: '39.7200 -104.9903',
                          country: 'US',
                          eeis: '["eei data 1", "eei data 2", "eei data 3", "eei data 4", "eei data 5"]')
    rfi3 = ImmRfi.create!(title: 'Test 3',
                   status: 'Closed',
                   priority: 'Routine',
                   justification: 'Lorem Ipsum is simply dummy text',
                   coordinates: '39.7200 -104.9903',
                   country: 'US',
                   eeis: '["eei data 1", "eei data 2", "eei data 3", "eei data 4", "eei data 5"]')
    List.create!(name: 'Test List', records: [])

    load_requests_tab

    within '.lists-bar' do
      find('.list-title', text: 'Test List').click
    end

    record1 = nil
    record2 = nil

    dropTarget = nil

    within '[data-aid=rfi-list]' do
      records = all('.list-table_item')
      record1 = records[0]
      record2 = records[1]
    end

    within '.custom-list' do
      dropTarget = find('.empty-list')
    end

    record1.drag_to dropTarget

    within '.custom-list' do
      expect(page).to have_content rfi3.title
      dropTarget = find('.list-table_item')
    end

    record2.drag_to dropTarget

    within '.custom-list' do
      customListRecords = all('.list-table_item')
      expect(customListRecords[0]).to have_content rfi2.title
    end

  end

  it 'sorts by date or title, ascending and descending' do
    load_requests_tab

    within '[data-aid=requirements-list]' do
      requirements = all('.list-table_item')
      expect(requirements[0]).to have_content('THIS IS LAST IN THE LIST RETURNED FROM PRISM BUT CREATED MOST RECENTLY')
      expect(page).to have_content('By: Date')
      expect(page).to have_content('arrow_downward')
      find('.sort').click
      expect(page).to have_content('Date')
      expect(page).to have_content('Title')
      sortOptions = all('.sort-option')
      sortOptions[1].click
      expect(page).to have_content('By: Title')
      expect(page).to have_content('arrow_downward')
      requirements = all('.list-table_item')
      expect(requirements[0]).to have_content('THIS IS LAST IN THE LIST RETURNED FROM PRISM BUT CREATED MOST RECENTLY')
      find('.sort_direction').click
      expect(page).to have_content('By: Title')
      expect(page).to have_content('arrow_upward')
      requirements = all('.list-table_item')
      expect(requirements[0]).to have_content('ACC 01 EO-IR-SAR RECON 1:2 20160921')
    end
  end

end