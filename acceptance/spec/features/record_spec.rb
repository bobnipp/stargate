require_relative '../spec_helper'
require_relative 'common_actions'

describe 'records', type: :feature, js: true do

  sidebar_animation_wait_time = 1

  describe 'sidebar actions' do
    defaultImmRfi = nil

    before do
      defaultImmRfi = ImmRfi.create!(title: 'War Never Changes',
                                     country: 'US',
                                     coordinates: '10 10',
                                     city: "city #{Random.rand}",
                                     created_on: '2018-08-12T13:00:00.000',
                                     region: "region #{Random.rand}",
                                     nai: "NAI #{Random.rand}",
                                     status: 'To Do',
                                     priority: 'Routine',
                                     justification: 'never say never',
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
                                     eeis: '["eei data 1", "eei data 2", "eei data 3", "eei data 4", "eei data 5"]',
                                     )

      load_requests_tab
    end

    it 'opens the sidebar when selecting an IMM record, then a PRISM record, then an IMM record' do
      find('.list-table_item', text: defaultImmRfi.title).click

      within '.sidebar' do
        expect(page).to have_button('Save', disabled: true)
        expect(page).to have_css('#priority')
        expect(page).to have_select('Priority', selected: 'Routine')
        titleRowNum = page.find('#title')[:rows].to_i
        expect(titleRowNum).to be == 1
      end

      find('.list-table_item', text: 'THIS IS LAST IN THE LIST RETURNED FROM PRISM BUT CREATED MOST RECENTLY').click

      within '.sidebar' do
        expect(page).not_to have_button('Save')
        expect(page).to have_css('#priority[disabled]')
        expect(page).to have_select('Priority', disabled: true, selected: 'ROUTINE')
        titleRowNum = page.find('#title')[:rows].to_i
        expect(titleRowNum).to be > 1
      end

      find('.list-table_item', text: defaultImmRfi.title).click

      within '.sidebar' do
        expect(page).to have_button('Save', disabled: true)
        expect(page).to have_css('#priority')
        expect(page).to have_select('Priority', selected: 'Routine')
        titleRowNum = page.find('#title')[:rows].to_i
        expect(titleRowNum).to be == 1
      end
    end

    it 'displays the abandon changes modal when the form has unsaved changes and is closed' do
      title = "this is the title #{Random.rand}"

      load_requests_tab
      find('#add-button').click

      within '.sidebar' do
        find('#title').send_keys title
        select 'Low', from: 'Priority'
        select 'To Do', from: 'Status'
      end

      find('#close-sidebar').click
      click_on 'NO, GO BACK'

      within '.sidebar' do
        expect(find('#title').value).to eq title
        expect(page).to have_select('Priority', selected: 'Low')
        expect(page).to have_select('Status', selected: 'To Do')
      end

      click_on 'Cancel'
      click_on 'YES, ABANDON'
      expect(page).to_not have_content('.sidebar')
    end

    it 'displays the more menu' do
      List.create!(name: 'Operation Karting', records: [])
      ImmRfi.make_rfi(title: 'Seasons Change', country: 'Spain')
      load_requests_tab

      find('.list-table_item', text: defaultImmRfi.title).click

      within '.sidebar' do
        sleep sidebar_animation_wait_time
        expect(page).not_to have_content('Add to List')

        find('.sidebar_action--menu').click
        expect(page).to have_content('Add to List')

        find('a', text: 'Add to List').click
        expect(page).to have_content('Operation Karting')

        find('a', text: 'Operation Karting').click
        expect(page).not_to have_content('Add to List')
      end

      find('.list-title', text: 'Operation Karting').click

      within '[data-aid="Operation Karting"]' do
        expect(page).to have_content "War Never Changes"
      end

      within('.list-table_item', text: 'Seasons Change') do
        expect(page).not_to have_content('Add to List')

        find('.list-table_item-action--menu').click
        expect(page).to have_content('Add to List')

        find('a', text: 'Add to List').click
        expect(page).to have_content('Operation Karting')

        find('a', text: 'Operation Karting').click
        expect(page).not_to have_content('Add to List')
      end

      within '[data-aid="Operation Karting"]' do
        expect(page).to have_content "War Never Changes"
        expect(page).to have_content "Seasons Change"
      end
    end
  end

  describe 'create' do
    it 'new IMM RFI' do
      title = "this is the title #{Random.rand}"
      country = "this is the country #{Random.rand}"
      city = "city #{Random.rand}"
      region = "region #{Random.rand}"
      coordinates = "595959N 1795959W"
      nai = "NAI #{Random.rand}"
      targetName1 = "Target Name #{Random.rand}"
      radius = 1.25
      targetCoordinates1 = "10 10"

      load_requests_tab

      find('#add-button').click

      within '.sidebar' do
        expect(page).to have_button('Save', disabled: true)
        find('#title').send_keys title
        expect(page).to have_button('Save', disabled: false)
        select 'Low', from: 'Priority'
        select 'To Do', from: 'Status'
        fill_in 'Justification', with: 'my description'
        fill_in 'Previous Research/Background', with: 'my Objective'

        click_on 'About'
        expect(page).not_to have_css('[data-aid=target-section]')
        select 'Requirement Type 1', from: 'Requirement Type'
        select 'SubWorkFlow 2', from: 'CRM Sub-Workflow'
        select 'Classification 3', from: 'Classification'
        fill_in 'Custom Classification', with: 'My Classification'
        select 'Caveat 4', from: 'Caveat'
        select 'Submitting Organization 5', from: 'Submitting Organization'
        fill_in 'POC(s)', with: 'Billy Bob, 123-456-7890, billy@bob.com'
        select 'NIPF Code 1', from: 'NIPF Code'
        select 'Commander PIR 2', from: 'PIR Name'
        select 'CENTCOM ISR Role 3', from: 'CENTCOM ISR Role'
        fill_in 'Operation', with: 'Operation Cheesecake'

        click_on 'Targets'
        expect(page).not_to have_css('[data-aid=about-section]')
        fill_in 'Country', with: country
        fill_in 'City', with: city
        fill_in 'Region', with: region
        fill_in 'Coordinates (1 vertex)', with: coordinates
        fill_in 'NAI (Named Area of Interest', with: nai
        fill_in 'Target Name', with: targetName1
        select 'Point', from: 'Target Type'
        fill_in 'Radius', with: radius
        select 'km', from: 'radius-unit-0'
        fill_in 'Target Coordinates', with: targetCoordinates1

        find('[data-aid=add-button-text]', text: 'Add a Target').click

        expect(page).to have_content 'Target #2'

        click_on 'Sensors'
        expect(page).not_to have_css('[data-aid=about-section]')
        fill_in 'Sensor', with: 'Sensor'
        select 'EO', from: 'Sensor Type'
        fill_in 'Desired Quality (NIIRS)', with: 10
        fill_in 'Mode', with: 'mode'
        fill_in 'Required Quality (NIIRS)', with: 5

        find('[data-aid=add-button-text]', text: 'Add a Sensor').click

        expect(page).to have_content 'Sensor #2'

        click_on 'Collection'
        fill_in 'Start Date', with: '12Jun2018'
        fill_in 'End Date', with: '23Sep2019'
        fill_in 'Collection Term', with: 'Col Term1'
        fill_in 'Collection Type', with: 'Col Type1'
        select 'Assigned Team 1', from: 'Assigned Team'
        fill_in 'Assignee', with: 'Assignee1'
        fill_in 'Guidance', with: 'Guidance1'

        fill_in 'EEI 1', with: 'EEI Data 1'

        expect(page).not_to have_content 'EEI 2'

        find('[data-aid=add-button-text]', text: 'Add an EEI').click

        expect(page).to have_content 'EEI 2'

        click_button 'Save'

        expect(page).to have_button('Save', disabled: true)
        expect(page).not_to have_content('PRISM')
      end

      expect(page).to have_selector('.btn-primary:disabled')
      expect(page).to have_selector('.sidebar')

      verify_refresh_verify do
        within '[data-aid=rfi-list]' do
          expect(page).to have_content title
        end
      end
    end
  end

  describe 'sidebar link' do

    it 'hides the links section when creating a new record, displays on save' do
      load_requests_tab

      find('#add-button').click

      within '.sidebar' do
        sleep sidebar_animation_wait_time
        expect(page).to have_button('Save', disabled: true)
        expect(page).to_not have_content('Add a Link')

        find('#title').send_keys title
        expect(page).to have_button('Save', disabled: false)
        select 'Low', from: 'Priority'
        select 'To Do', from: 'Status'
        fill_in 'justification', with: 'some justification'

        click_button 'Save'
        expect(page).to have_button('Save', disabled: true)

        expect(page).to have_content('Add a Link')
      end
    end

    it 'can add and delete a link to an existing rfi and a PRISM requirement on an existing rfi' do
      rfi = ImmRfi.create!(title: "Lotus", country: 'US', status: 'TO_DO', justification: 'sure', priority: 'Low', eeis: '["eei data 1", "eei data 2", "eei data 3"]')
      rfi1 = ImmRfi.create!(title: "Link Test 1", country: 'US', status: 'TO_DO', justification: 'justification', priority: 'Low', eeis: '["eei data 1", "eei data 2", "eei data 3"]')
      rfi2 = ImmRfi.create!(title: "Link Test 2", country: 'US', status: 'TO_DO', justification: 'justification', priority: 'Low', eeis: '["eei data 1", "eei data 2", "eei data 3"]')

      load_requests_tab

      find('.list-table_item', text: rfi.title).click

      within '.sidebar' do
        sleep sidebar_animation_wait_time
        expect(page).to have_button('Save', disabled: true)
        find('[data-aid=add-button-text]', text: 'Add a Link').click

        within '#link-info' do
          select 'IMM', from: 'Originating System'
          find('#id').send_keys rfi1.id
          click_button 'Add'
        end

        find('[data-aid=add-button-text]', text: 'Add a Link').click

        within '#link-info' do
          select 'PRISM', from: 'Originating System'
          find('#id').send_keys 'ACC 01 EO-IR-SAR RECON 1:2 20160921'
          click_button 'Add'
        end

        find('[data-aid=add-button-text]', text: 'Add a Link').click

        within '#link-info' do
          select 'IMM', from: 'Originating System'
          fill_in 'id', with: 'invalid id'
          expect(page).to have_content 'Cannot find ID'

          fill_in 'id', with: rfi2.id
          expect(page).to_not have_content 'Cannot find ID'
          click_button 'Add'
        end

        links = all('.links-list-item')
        expect(links[0]).to have_content 'IMM'
        expect(links[0]).to have_content "##{rfi1.id}"
        expect(links[0]).to have_content rfi1.title

        expect(links[1]).to have_content 'PRISM'
        expect(links[1]).to have_content '#ACC 01 EO-IR-SAR RECON 1:2 20160921'
        expect(links[1]).to have_content 'ACC 01 EO-IR-SAR RECON 1:2 20160921'
        expect(links[1]).to have_content 'APPROVE'

        expect(links[2]).to have_content 'IMM'
        expect(links[2]).to have_content "##{rfi2.id}"
        expect(links[2]).to have_content rfi2.title

        expect(page).to have_button('Save', disabled: true)
      end

      find('.list-table_item', text: 'Lotus').click

      within '.sidebar' do
        sleep sidebar_animation_wait_time
        find('#link-menu-2', wait: 3).click
        expect(page).to have_css('#link-delete-2')
        find('#link-delete-2').click

        imm_link = find('.links-list-item', text: 'IMM')
        prism_link = find('.links-list-item', text: 'PRISM')

        expect(imm_link).to have_content "##{rfi1.id}"
        expect(imm_link).to have_content rfi1.title

        expect(prism_link).to have_content '#ACC 01 EO-IR-SAR RECON 1:2 20160921'
        expect(prism_link).to have_content 'ACC 01 EO-IR-SAR RECON 1:2 20160921'
        expect(prism_link).to have_content 'APPROVE'
      end
    end

    it 'displays potential matches for Prism' do
      rfi1 = ImmRfi.create!(title: "Link Test 1", country: 'US', status: 'TO_DO', eeis: '["eei data 1", "eei data 2", "eei data 3"]')
      load_requests_tab
      find('.list-table_item', text: rfi1.title).click

      within '.sidebar' do
        sleep sidebar_animation_wait_time
        find('[data-aid=add-button-text]', text: 'Add a Link').click
        within '#link-info' do
          select 'PRISM', from: 'Originating System'
          find('#id').send_keys 'ACC'
        end

        within '.predicted-links' do
          find('.link', text: 'ACC 01 EO-IR-SAR RECON 1:2 20160921').click
        end

        within '#link-info' do
          expect(find('#id').value).to eq 'ACC 01 EO-IR-SAR RECON 1:2 20160921'
        end
      end
    end

    it 'displays potential matches for IMM' do
      rfi1 = ImmRfi.create!(title: "Link Test 1", country: 'US', status: 'TO_DO', eeis: '["eei data 1", "eei data 2", "eei data 3"]')
      rfi2 = ImmRfi.create!(title: "Link Test 2", country: 'US', status: 'WORKING', eeis: '["eei data 1", "eei data 2", "eei data 3"]')

      load_requests_tab

      find('.list-table_item', text: rfi1.title).click

      within '.sidebar' do
        sleep sidebar_animation_wait_time
        find('[data-aid=add-button-text]', text: 'Add a Link').click

        within '#link-info' do
          find_field('ID', disabled: true)
          select 'IMM', from: 'Originating System'
          find_field('ID', disabled: false)

          find('#id').send_keys '2'
        end

        within '.predicted-links' do
          find('.link', text: '2').click
        end

        within '#link-info' do
          click_button 'Add'
        end

        links = all('.links-list-item')
        expect(links[0]).to have_content 'IMM'
        expect(links[0]).to have_content "##{rfi2.id}"
        expect(links[0]).to have_content rfi2.title
      end

      within '.sidebar' do
        find('[data-aid=add-button-text]', text: 'Add a Link').click

        within '#link-info' do
          select 'IMM', from: 'Originating System'
          find('#id').send_keys '1'
        end

        expect(page).not_to have_content('.predicted-links')
      end
    end
  end

  describe 'rfi details' do
    title = 'War Never Changes'
    country = 'US'
    coordinates = "10 10"
    city = "city #{Random.rand}"
    region = "region #{Random.rand}"
    nai = "NAI #{Random.rand}"
    immRfi = nil
    targetName1 = "Target Name #{Random.rand}"
    targetName2 = "Target Name #{Random.rand}"
    radius1 = "#{Random.rand.round(6)}"
    radius2 = "#{Random.rand.round(6)}"
    targetCoordinates1 = "10 10"
    targetCoordinates2 = "20 20"

    before do
      immRfi = ImmRfi.create!(title: title,
        country: country,
        coordinates: coordinates,
        city: city,
        created_on: '2018-08-12T13:00:00.000',
        region: region,
        nai: nai,
        status: 'To Do',
        priority: 'Routine',
        justification: 'never say never',
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

      Target.create!(imm_rfi_id: immRfi.id, name: targetName1, target_type: 'POINT', radius: radius1, radius_unit: 'KM', coordinates: targetCoordinates1)
      Target.create!(imm_rfi_id: immRfi.id, name: targetName2, target_type: 'POINT', radius: radius2, radius_unit: 'KM', coordinates: targetCoordinates2)
      Sensor.create!(imm_rfi_id: immRfi.id, sensor: 'sensor', sensor_type: 'SAR', mode: 'mode', desired_quality: 10, required_quality: 5)

      RecordHistory.create!(username: 'hardcoded', action: 'Insert', rfi_id: immRfi.id)

      load_requests_tab
    end

    it 'opens and closes rfi details display' do
      find('.list-table_item', text: 'War Never Changes').click

      expect(find('.list-table_item', text: 'War Never Changes')).to have_css('.active')

      expected_time = nil
      within '.sidebar' do
        expect(page).to have_content("ID ##{immRfi.id}")
        click_on 'Sensors'
        expect(find('#sensor-0').value).to eq 'sensor'
        expect(find('#sensor-type-0').value).to eq 'SAR'
        expect(find('#sensor-mode-0').value).to eq 'mode'
        expect(find('#sensor-rq-0').value).to eq '5'
        expect(find('#sensor-dq-0').value).to eq '10'

        click_on 'Targets'
        expect(find('#title').value).to eq title
        expect(find('#coordinates').value).to eq coordinates

        within '#basic-coordinates' do
          expect(find('i')).to have_content('info_outline')
        end

        expect(find('#city').value).to eq city
        expect(find('#region').value).to eq region
        expect(find('#nai').value).to eq nai

        within '[data-aid=target-0]' do
          expect(find('#target-name-0').value).to eq targetName1
          expect(page).to have_select('Target Type', selected: 'Point')
          expect(find('#radius-0').value).to eq radius1.to_s
          expect(find('#radius-unit-0').value).to eq 'KM'
          expect(find('#target-coordinates-0').value).to eq targetCoordinates1
        end

        within '[data-aid=target-1]' do
          expect(find('#target-name-1').value).to eq targetName2
          expect(page).to have_select('Target Type', selected: 'Point')
          expect(find('#radius-1').value).to eq radius2.to_s
          expect(find('#radius-unit-1').value).to eq 'KM'
          expect(find('#target-coordinates-1').value).to eq targetCoordinates2
        end

        click_on 'About'
        expect(page).to have_select('Requirement Type', selected: 'Requirement Type 2')
        expect(page).to have_select('CRM Sub-Workflow', selected: 'SubWorkFlow 2')
        expect(page).to have_select('Classification', selected: 'Classification 2')
        expect(page).to have_select('Caveat', selected: 'Caveat 2')
        expect(page).to have_select('Submitting Organization', selected: 'Submitting Organization 2')
        expect(page).to have_select('NIPF Code', selected: 'NIPF Code 2')
        expect(page).to have_select('PIR Name', selected: 'Commander PIR 2')
        expect(page).to have_select('CENTCOM ISR Role', selected: 'CENTCOM ISR Role 2')
        expect(find('#customClassification').value).to eq 'vault 101'
        expect(find('#poc').value).to eq 'The Institute'
        expect(find('#operation').value).to eq 'Blow up the world'

        expect(page).to have_select('Priority', selected: 'Routine')
        expect(page).to have_select('Status', selected: 'To Do')
        expect(page).to have_field('Justification', with: 'never say never')
        expect(page).to have_field('Previous Research/Background', with: 'say never sometimes')

        click_on 'Collection'
        expect(find_field('collectionStartDate').value).to match(/12 Aug 2018(.*+)/)
        expect(find_field('collectionEndDate').value).to match(/15 Nov 2018(.*+)/)
        expect(find_field('collectionType').value).to match('collection type1')
        expect(find_field('collectionTerm').value).to match('collection term1')
        expect(page).to have_select('Assigned Team', selected: 'Assigned Team 2')
        expect(find_field('assignee').value).to match('assignee1')
        expect(find_field('collectionGuidance').value).to match('collection guidance1')
        expect(find_field('EEI 1').value).to match('eei data 1')
        expect(find_field('EEI 2').value).to match('eei data 2')
        expect(find_field('EEI 3').value).to match('eei data 3')
        expect(find_field('EEI 4').value).to match('eei data 4')
        expect(find_field('EEI 5').value).to match('eei data 5')

        freeze_time do
          expected_time = Time.current.strftime('%b %-e, %-l:%M %p')
          click_on 'Activity'
          expect(page).to have_content('@user1')
          expect(page).to have_field('addComment')
          expect(page).to have_button('Post', disabled: true)

          fill_in 'addComment', with: 'No comment'
          click_on 'Post'
        end
        within '.rfi-activities' do
          expect(page).to have_content('No comment')
          expect(page).to have_content(expected_time)
        end
        expect(page).to have_button('Post', disabled: true)

        click_on 'Details'
        expect(find_field('collectionStartDate').value).to match(/12 Aug 2018(.*+)/)

        click_on 'History'
        expect(page).to have_content('Created')
        expect(page).to have_content('hardcoded')
      end

      load_requests_tab

      find('.list-table_item', text: 'War Never Changes').click
      within '.sidebar' do
        sleep sidebar_animation_wait_time
        click_on 'Activity'
        within '.rfi-activities' do
          expect(page).to have_content('No comment')
          expect(page).to have_content(expected_time)
        end
      end

      find('.sidebar_action--close', match: :first).click
      expect(page).not_to have_selector '.sidebar'
    end

    it 'can edit an rfi' do
      find('.list-table_item', text: 'War Never Changes').click

      within '.sidebar' do
        expect(page).to have_button('Save', disabled: true)
        fill_in 'title', with: ''
        find('#title').send_keys 'War Sometimes Changes'
        expect(page).to have_button('Save', disabled: false)

        select 'Low', from: 'Priority'
        select 'Working', from: 'Status'

        fill_in 'Justification', with: 'foobar'
        fill_in 'Previous Research/Background', with: 'fizz buzz'

      end

      expect(page).to have_content 'War Never Changes'

      within '.sidebar' do
        click_button 'Save'
      end

      expect(page).to have_selector('.btn-primary:disabled')
      expect(page).to have_selector('.sidebar')

      expect(page).not_to have_content 'War Never Changes'
      expect(page).to have_content 'War Sometimes Changes'
      expect(page).to have_content 'Working'

      visit '/'
      click_tab 'view_list'

      expect(page).not_to have_content 'War Never Changes'
      find('.list-table_item', text: 'War Sometimes Changes').click

      within '.sidebar' do
        expect(find('#title').value).to eq 'War Sometimes Changes'

        expect(page).to have_select('Priority', selected: 'Low')
        expect(page).to have_select('Status', selected: 'Working')
        expect(page).to have_field('Justification', with: 'foobar')
        expect(page).to have_field('Previous Research/Background', with: 'fizz buzz')
      end
    end

    it 'displays the abandon changes modal when clicking another rfi in the list' do
      ImmRfi.make_rfi(title: 'This is here too', country: 'Somewhere')

      load_requests_tab

      find('.list-table_item', text: 'War Never Changes').click

      within '.sidebar' do
        select 'Immediate', from: 'Priority'
      end

      find('.list-table_item', text: 'This is here too').click

      expect(page).to have_button('NO, GO BACK')

      click_on('YES, ABANDON')

      within('.sidebar') do
        expect(find('#title').value).to eq 'This is here too'
      end
    end

    it 'creates bi-directional links between RFIs and PRISM noms' do
      rfi1 = ImmRfi.make_rfi(title: "Link Test 1", country: 'US', status: 'TO_DO')
      rfi2 = ImmRfi.make_rfi(title: "Link Test 2", country: 'US', status: 'TO_DO')
      load_requests_tab
      find('.list-table_item', text: 'War Never Changes').click

      within '.sidebar' do
        sleep sidebar_animation_wait_time
        find('[data-aid=add-button-text]', text: 'Add a Link').click
        within '#link-info' do
          select 'IMM', from: 'Originating System'
          fill_in 'ID', with: rfi1.id
          click_button 'Add'
        end

        find('[data-aid=add-button-text]', text: 'Add a Link').click
        within '#link-info' do
          select 'PRISM', from: 'Originating System'
          find('#id').send_keys 'ACC 01 EO-IR-SAR RECON 1:2 20160921'
          click_button 'Add'
        end

        find('[data-aid=add-button-text]', text: 'Add a Link').click
        within '#link-info' do
          select 'IMM', from: 'Originating System'
          fill_in 'ID', with: rfi2.id
          click_button 'Add'
        end

        within '.existing-links' do
          expect(page).to have_content 'IMM'
          expect(page).to have_content "##{rfi1.id}"
          expect(page).to have_content rfi1.title
          expect(page).to have_content 'TO DO'

          expect(page).to have_content "##{rfi2.id}"
          expect(page).to have_content rfi2.title

          expect(page).to have_content 'PRISM'
          expect(page).to have_content '#ACC 01 EO-IR-SAR RECON 1:2 20160921'
          expect(page).to have_content 'ACC 01 EO-IR-SAR RECON 1:2 20160921'
          expect(page).to have_content 'APPROVE'

          find('#link-menu-2').click
          find('#link-delete-2').click
        end
      end

      visit '/'
      click_tab 'view_list'
      find('.clickable', text: 'War Never Changes').click

      expect(page).to have_css('.sidebar')
      within '.sidebar' do
        expect(page).to have_content 'IMM'
        expect(page).to have_content "##{rfi1.id}"
        expect(page).to have_content rfi1.title

        expect(page).to have_content 'PRISM'
        expect(page).to have_content '#ACC 01 EO-IR-SAR RECON 1:2 20160921'
        expect(page).to have_content 'ACC 01 EO-IR-SAR RECON 1:2 20160921'
        expect(page).to have_content 'APPROVE'

        expect(page).not_to have_content "##{rfi2.id}"
        expect(page).not_to have_content rfi2.title
      end

      find('#close-sidebar').click
      find('.list-table_item', text: rfi1.title).click
      within '.sidebar' do
        expect(find('.sidebar-header_record_id')).to have_content "##{rfi1.id}"
        link = find('.links-list-item')
        expect(link).to have_content 'IMM'
        expect(link).to have_content "##{immRfi.id}"
        expect(link).to have_content immRfi.title
      end

      find('#close-sidebar').click
      find('.list-table_item', text: 'ACC 01 EO-IR-SAR RECON 1:2 20160921').click
      within '.sidebar' do
        sleep sidebar_animation_wait_time
        find('[data-aid=add-button-text]', text: 'Add a Link').click
        select 'IMM', from: 'Originating System'
        fill_in 'ID', with: rfi2.id
        click_button 'Add'
      end

      find('.list-table_item', text: 'Link Test 2').click
      within '.sidebar' do
        sleep sidebar_animation_wait_time
        within '.existing-links' do
          expect(page).to have_content 'PRISM'
          expect(page).to have_content 'ACC 01 EO-IR-SAR RECON 1:2 20160921'
        end
      end

      find('#close-sidebar').click
      find('.list-table_item', text: 'ACC 01 EO-IR-SAR RECON 1:2 20160921').click
      within '.sidebar' do
        link = find('.links-list-item', text: 'Link Test 2')
        expect(link).to have_content 'IMM'
      end
    end

    it 'prevents a duplicate link from being added' do
      rfi1 = ImmRfi.create!(title: "Link Test 1", country: 'US', status: 'TO_DO', eeis: '["eei data 1", "eei data 2", "eei data 3"]')
      rfi2 = ImmRfi.create!(title: "Link Test 2", country: 'US', status: 'WORKING', eeis: '["eei data 1", "eei data 2", "eei data 3"]')
      ImmLink.create!(record_1_id: rfi2.id, record_1_system: 'IMM', record_2_id: rfi1.id, record_2_system: 'IMM')

      visit '/'
      click_tab 'view_list'
      find('.list-table_item', text: rfi1.title).click
      within '.sidebar' do
        sleep sidebar_animation_wait_time
        find('[data-aid=add-button-text]', text: 'Add a Link').click
        select 'IMM', from: 'Originating System'
        fill_in 'ID', with: rfi2.id
        click_button 'Add'
        sleep 3
        expect(find('.links-list-item', text: 'Link Test 2')).to be_truthy
      end
    end

    it 'can open the link to another RFI in the sidebar' do
      rfi1 = ImmRfi.create!(title: "Link Test 1", country: 'US', status: 'TO_DO', eeis: '["eei data 1", "eei data 2", "eei data 3"]')
      rfi2 = ImmRfi.create!(title: "Link Test 2", country: 'US', status: 'WORKING', eeis: '["eei data 1", "eei data 2", "eei data 3"]')
      ImmLink.create!(record_1_system: 'IMM', record_1_id: rfi2.id, record_2_id: rfi1.id, record_2_system: 'IMM')

      visit '/'
      click_tab 'view_list'
      find('.list-table_item', text: rfi2.title).click
      within '.sidebar' do
        sleep sidebar_animation_wait_time
        find('.link-id').click
        expect(page).to have_content "ID ##{rfi1.id}"
      end
    end

    it 'can open the link to another RFI in the sidebar when rfiForm is dirty' do
      rfi1 = ImmRfi.make_rfi(title: "Link Test 1", country: 'US', status: 'To Do', eeis: '["eei data 1", "eei data 2", "eei data 3"]')
      rfi2 = ImmRfi.make_rfi(title: "Link Test 2", country: 'US', status: 'Working', eeis: '["eei data 1", "eei data 2", "eei data 3"]')
      ImmLink.create!(record_1_system: 'IMM', record_1_id: rfi2.id, record_2_id: rfi1.id, record_2_system: 'IMM')

      visit '/'
      click_tab 'view_list'
      find('.list-table_item', text: rfi2.title).click
      within '.sidebar' do
        select 'Immediate', from: 'Priority'
        find('.link-id').click
      end

      expect(page).to have_content('YES, ABANDON')
      click_button 'YES, ABANDON'

      within '.sidebar' do
        expect(page).to have_content "ID ##{rfi1.id}"
      end
    end
  end

  describe 'rfi activities' do
    title = "this is the title #{Random.rand}"

    it 'can add and delete a comment to an existing rfi' do
      rfi = ImmRfi.make_rfi(title: "Link Test 1", country: 'US', status: 'To Do', eeis: '["eei data 1", "eei data 2", "eei data 3"]')
      load_requests_tab

      find('.list-table_item', text: rfi.title).click

      within '.sidebar' do
        sleep sidebar_animation_wait_time

        click_on 'Activity'
        fill_in 'addComment', with: 'This is a comment comment'
        click_on 'Post'
        find('.activities-menu').click
        within '.menu' do
          find('.delete').click
        end
        expect(page).not_to have_content('This is a comment')
      end
    end
  end

  describe 'rfi history' do
    it 'shows the history when the RFI tab is selected' do
      title = 'History'
      country = 'Country'

      load_requests_tab
      find('#add-button').click

      within '.sidebar' do
        find('#title').send_keys title
        fill_in 'justification', with: 'some justification'
        select 'Immediate', from: 'Priority'

        click_button 'Save'

        sleep(1)
        click_on 'History'
        expect(page).to have_content('Created')

        click_on 'Details'
        click_on 'Targets'
        fill_in 'Country', with: country
        click_button 'Save'

        sleep(1)
        click_on 'History'
        expect(page).to have_content('Modified')
      end
    end
  end

  describe 'expand and collapse details' do
    it 'expands and collapses details and links' do
      rfi1 = ImmRfi.create!(title: "Link Test 1", country: 'US', status: 'TO_DO', eeis: '["eei data 1", "eei data 2", "eei data 3"]')
      load_requests_tab
      find('.list-table_item', text: rfi1.title).click

      within '.sidebar' do
        expect(page).to have_content 'Add a Link'
        expect(page).to have_button 'About'

        find('.form-group_label-open', :text => 'Links').click
        expect(page).not_to have_content 'Add a Link'

        find('.form-group_label-open', :text => 'Info').click
        expect(page).not_to have_button 'ABOUT'

        find('.form-group_label-closed', :text => 'Links').click
        expect(page).to have_content 'Add a Link'

        find('.form-group_label-closed', :text => 'Info').click
        expect(page).to have_button 'About'
      end
    end
  end

  describe 'required fields & validation' do

    it 'alerts user if a required field empty' do
      load_requests_tab

      find('#add-button').click

      within '.sidebar' do
        expect(page).to have_button('Save', disabled: true)
        fill_in 'Previous Research/Background', with: 'my Objective'
        expect(page).to have_button('Save', disabled: false)

        click_button 'Save'

        expect(page).to have_content('Please select a priority')
        expect(page).to have_content('Please include a title')
        expect(page).to have_content('Please include a justification')

        within '.sidebar-header_error-collection' do
          expect(page).to have_content('Unable to Save')
          expect(page).to have_content('Please include a priority')
          expect(page).to have_content('Please include a title')
          expect(page).to have_content('Please include a justification')
        end
      end

      find('#add-button').click
      click_on 'YES, ABANDON'

      within '.sidebar' do
        expect(page).not_to have_content('Please select a priority')
        expect(page).not_to have_content('Please include a title')
        expect(page).not_to have_content('Please include a justification')

        expect(page).not_to have_css('.sidebar-header_error-collection')
      end

    end

    it 'does not validate coordinates if none have been entered' do
      load_requests_tab

      find('#add-button').click

      within '.sidebar' do
        expect(page).to have_button('Save', disabled: true)

        click_on 'Targets'
        select 'Area', from: 'Target Type'
        fill_in 'coordinates', with: ''
        # do not fill in coordinates
        expect(page).not_to have_content('Does not match Target Type')
      end
    end

    it 'alerts user if entered coordinates are incorrect' do
      load_requests_tab
      find('#add-button').click

      within '.sidebar' do
        expect(page).to have_button('Save', disabled: true)
        find('#title').send_keys title
        fill_in 'justification', with: 'some justification'
        select 'Low', from: 'Priority'

        click_on 'Targets'
        fill_in 'coordinates', with: 'bad coordinates'
        fill_in 'city', with: 'blah'
        expect(page).to have_content('Coordinates are invalid, check for spaces and that vertices are separated by a comma')
        expect(page).to have_button('Save', disabled: false)

        fill_in 'coordinates', with: '40.446 -179.982, 34.34 -129.2342344; -12.234 154.234344'
        fill_in 'city', with: 'blah blah'
        expect(page).to have_content('Only 1 vertex allowed')

        fill_in 'coordinates', with: '40.446 -179.982'

        within '[data-aid=target-0]' do
          select 'Area', from: 'Target Type'
          fill_in 'Target Coordinates', with: '40.446 -179.982, 34.34 -129.2342344, 32.34 -128.2342344, 24.34 -120.2342344'
          fill_in 'Target Name', with: 'Valid polygon 4 points'
          expect(page).not_to have_content 'Does not match Target Type'

          fill_in 'Target Coordinates', with: '40.446 -179.982, 34.34 -129.2342344, 32.34 -128.2342344, 24.34 -120.2342344'
          fill_in 'Target Name', with: 'Valid polygon 3 points'
          expect(page).not_to have_content 'Does not match Target Type'

          fill_in 'Target Coordinates', with: '40.446 -179.982, 34.34 -129.2342344'
          fill_in 'Target Name', with: 'Invalid polygon'
          expect(page).to have_content 'Does not match Target Type'

          select 'Point', from: 'Target Type'
          fill_in 'Target Name', with: 'Invalid point'
          expect(page).to have_content 'Does not match Target Type'

          fill_in 'Target Coordinates', with: '40.446 -179.982'
          fill_in 'Target Name', with: 'Valid point'
          expect(page).not_to have_content 'Does not match Target Type'
        end

        expect(page).to have_button('Save', disabled: false)
        click_button 'Save'
      end
    end

    it 'allows user to save when all required fields are present' do
      title = "this is the title #{Random.rand}"

      load_requests_tab
      find('#add-button').click

      within '.sidebar' do
        expect(page).to have_button('Save', disabled: true)
        find('#title').send_keys title
        fill_in 'justification', with: 'some justification'
        select 'Low', from: 'Priority'
        expect(page).to have_button('Save', disabled: false)

        click_button 'Save'

        expect(page).to have_button('Save', disabled: true)

        expect(page).not_to have_content('Please select a priority')
        expect(page).not_to have_content('Please include a title')
        expect(page).not_to have_content('Please include a justification')

      end
    end
  end

  describe 'sensors' do
    it 'can delete a sensor' do
      rfi1 = ImmRfi.create!(title: "Sensor Test 1", country: 'US', status: 'TO_DO', eeis: '["eei data 1", "eei data 2", "eei data 3"]')
      Sensor.create!(imm_rfi_id: rfi1.id, sensor: 'sensor 1', sensor_type: 'SAR', mode: 'mode', desired_quality: 10, required_quality: 5)
      Sensor.create!(imm_rfi_id: rfi1.id, sensor: 'sensor 2', sensor_type: 'EO', mode: 'mode', desired_quality: 10, required_quality: 5)

      load_requests_tab

      within '[data-aid=rfi-list]' do
        find('.list-table_item', text: rfi1.title).click
      end

      within '.sidebar' do
        click_on 'Sensors'
        expect(page).to have_content('Sensor #1')
        expect(page).to have_content('Sensor #2')

        find('#sensor-menu-0').click
        find('#sensor-delete-0').click

        expect(page).to have_content('Sensor #1')
        expect(find_field('Sensor').value).to match('sensor 2')
        expect(page).not_to have_content('Sensor #2')

        click_button 'Cancel'
      end

      within '[data-aid=rfi-list]' do
        find('.list-table_item', text: rfi1.title).click
      end

      within '.sidebar' do
        click_on 'Sensors'
        expect(page).to have_content('Sensor #1')
        expect(find_field('Sensor').value).to match('sensor 2')
        expect(page).not_to have_content('Sensor #2')
      end
    end
  end

  describe 'targets' do
    it 'can add and delete a target' do
      load_requests_tab
      find('#add-button').click

      within '.sidebar' do
        sleep sidebar_animation_wait_time
        click_on 'Targets'
        expect(page).to have_content('Target #1')
        expect(page).not_to have_content('Target #2')

        find('[data-aid=add-button-text]', text: 'Add a Target').click

        expect(page).to have_content('Target #1')
        expect(page).to have_content('Target #2')

        find('#target-menu-1').click
        find('#target-delete-1').click

        expect(page).to have_content('Target #1')
        expect(page).not_to have_content('Target #2')
      end
    end

    it 'pops up info-dialog when icon is selected' do
      load_requests_tab
      find('#add-button').click

      within '.sidebar' do
        sleep sidebar_animation_wait_time
        click_on 'Targets'

        within '#basic-coordinates' do
          find('i').click
        end
        expect(page).to have_content('Acceptable Formats:')
        click_on 'Details'
        expect(page).not_to have_content('Acceptable Formats:')

        within '#target-coordinates-div-0' do
          find('i').click
        end
        expect(page).to have_content('Acceptable Formats:')
        click_on 'Details'
        expect(page).not_to have_content('Acceptable Formats:')
      end
    end

    it 'displays individually popovers for multiple targets (one at a time)' do
      load_requests_tab
      find('#add-button').click

      within '.sidebar' do
        sleep sidebar_animation_wait_time
        click_on 'Targets'
        find('[data-aid=add-button-text]', text: 'Add a Target').click

        within '#target-coordinates-div-0' do
          find('i').click
        end

        popovers = all('.popover')
        expect(popovers.size).to eq(1)

        within '#target-coordinates-div-1' do
          find('i').click
        end

        popovers = all('.popover')
        expect(popovers.size).to eq(1)
      end

    end
  end

  describe 'create link lists' do
    it 'opens and closes lists with record links' do

      ImmRfi.create!(
        title: 'title',
        id: 1,
        justification: 'justification',
        status: 'To Do',
        coordinates: '39.7410 -104.9903',
        country: 'US'
      )

      ImmRfi.create!(
        title: 'title 2',
        id: 2,
        justification: 'justification',
        status: 'To Do',
        coordinates: '39.7410 -104.9903',
        country: 'US'
      )

      ImmRfi.create!(
          title: 'title 3',
          id: 3,
          justification: 'justification',
          status: 'To Do',
          coordinates: '39.7410 -104.9903',
          country: 'US'
      )
      ImmRfi.create!(
          title: 'title 4',
          id: 4,
          justification: 'justification',
          status: 'To Do',
          coordinates: '39.7410 -104.9903',
          country: 'US'
      )
      ImmLink.create!(
        id: 1,
        record_1_id: 1,
        record_2_id: 2,
        record_1_system: 'IMM',
        record_2_system: 'IMM'
      )
      ImmLink.create!(
        id: 2,
        record_1_id: 3,
        record_2_id: 4,
        record_1_system: 'IMM',
        record_2_system: 'IMM'
      )

      load_requests_tab
      find('#add-button').click

      within '[data-aid=rfi-list]' do
        rfi_items = all('.list-table_item')

        expect(rfi_items[0]).to have_css('.open-link-list')

        expect(page).not_to have_css('#link-list-2')
        expect(page).not_to have_css('#link-list-4')

        # open the first link (title 4)
        within rfi_items[0] do
          page.find('.open-link-list').click
          expect(page).to have_css('.open-link-list_active')
        end

        # open the second link (title 2)
        within rfi_items[2] do
          page.find('.open-link-list').click
          expect(page).to have_css('.open-link-list_active')
        end

      end

      expect(page).not_to have_css('.sidebar')

      # should be 2 lists (ids=2,4)
      link_lists = all('.links-list')
      expect(link_lists.size).to eq(2)

      # ordering should be "title 2", and THEN "title 4"
      expect(link_lists[0]).to have_content("title 2")
      expect(link_lists[1]).to have_content("title 4")

      # close the lists
      within link_lists[0] do
        expect(page).to have_content 'title 2'
        find('.list_action').click
      end

      link_lists = all('.links-list')
      expect(link_lists.size).to eq(1)
      expect(link_lists[0]).to have_content("title 4")

      within link_lists[0] do
        expect(page).to have_content 'title 4'
        find('.list_action').click
      end

      link_lists = all('.links-list')
      expect(link_lists.size).to eq(0)

      # all of the 'active' icons are gone
      within '[data-aid=rfi-list]' do
        expect(page).not_to have_css('.open-link-list_active')
      end

    end

    it 'does not display an icon if there are no links' do
      ImmRfi.create!(
        title: 'title',
        id: 1,
        justification: 'justification',
        status: 'To Do',
        coordinates: '39.7410 -104.9903',
        country: 'US'
      )

      load_requests_tab

      within '[data-aid=rfi-list]' do
        rfi_items = all('.list-table_item')

        expect(rfi_items[0]).not_to have_css('.open-link-list')
      end
    end

    it 'can remove an eei' do
      rfi1 = ImmRfi.create!(title: "Link Test 1", priority: 'Low', coordinates: '', justification: 'test', country: 'US', status: 'TO_DO', eeis: '["eei data 1", "eei data 2", "eei data 3"]')

      load_requests_tab

      within '[data-aid=rfi-list]' do
        find('.list-table_item', text: rfi1.title).click
      end

      within '.sidebar' do
        sleep sidebar_animation_wait_time
        click_on 'Collection'
        eeis = all('.eei')
        expect(find_field('EEI 3').value).to match('eei data 3')

        within eeis[0] do
          expect(page).not_to have_css '.eei-menu'
        end

        within eeis[1] do
          find('.eei-menu').click
          find('.delete').click
        end

        expect(find_field('EEI 2').value).to match('eei data 3')

        click_button 'Save'
        expect(page).to have_button('Save', disabled: true)
        click_button 'Cancel'
      end

      within '[data-aid=rfi-list]' do
        find('.list-table_item', text: rfi1.title).click
      end

      within '.sidebar' do
        sleep sidebar_animation_wait_time
        click_on 'Collection'
        expect(find_field('EEI 2').value).to match('eei data 3')
      end
    end
  end
end