require_relative '../spec_helper'
require_relative 'common_actions'

describe 'prism collection requirements', type: :feature, js: true do
  sidebar_animation_wait_time = 1

  it 'displays existing prism requirements in descending order of creation' do
    load_requests_tab

    within '[data-aid=requirements-list]' do
      requirements = all('.list-table_item')

      expect(requirements.size).to eq(2)

      #We have a bunch of duplicates because of the way we mock responses from PRISM - feel free to ask Jeremy
      expect(requirements[0]).to have_content('THIS IS LAST IN THE LIST RETURNED FROM PRISM BUT CREATED MOST RECENTLY')
      expect(requirements[0]).to have_content("NOM ID #THIS IS LAST IN THE LIST RETURNED FROM PRISM BUT CREATED MOST RECENTLY")
      expect(requirements[0]).to have_content('SUBMITTED')
      expect(requirements[0]).to have_content('Another justification')

      expect(requirements[1]).to have_content('ACC 01 EO-IR-SAR RECON 1:2 20160921')
      expect(requirements[1]).to have_content("NOM ID #ACC 01 EO-IR-SAR RECON 1:2 20160921")
      expect(requirements[1]).to have_content('APPROVE')
      expect(requirements[1]).to have_content('NOM Justification')
    end
  end

  it 'disables form for prism records and enables for imm records' do
    ImmRfi.create!(title: 'Test RFI Enabled',
                   country: 'USA',
                   created_on: '2018-08-12T13:00:00.000',
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
                   eeis: '["eei data 1", "eei data 2", "eei data 3"]'
    )

    load_requests_tab
    find('.clickable', text: 'ACC 01 EO-IR-SAR RECON 1:2 20160921', match: :first).click
    find_field('Justification', disabled: true)

    find('#close-sidebar').click

    find('.clickable', text: 'Test RFI Enabled', match: :first).click
    find_field('Justification', disabled: false)
  end

  describe 'collection requirement details view' do

    it 'opens and closes rfi details display' do
      load_requests_tab

      find('.clickable', text: 'ACC 01 EO-IR-SAR RECON 1:2 20160921', match: :first).click

      within '.sidebar' do
        sleep sidebar_animation_wait_time
        expect(page).to have_content('PRISM')
        expect(page).to have_content('ACC 01 EO-IR-SAR RECON 1:2 20160921')

        expect(page.find('#priority').value).to eq 'IMMEDIATE'
        expect(page.find('#status').value).to eq 'APPROVE'
        expect(find_field('Justification', disabled: true).value).to eq 'NOM Justification'
        expect(find_field('Previous Research/Background', disabled: true).value).to include 'NOM Comments/Special Instructions'

        click_on 'About'
        expect(find_field('POC(s)', disabled: true).value).to eq 'DLARSEN (Derek), RANDREWS (randrews)'

        click_on 'Collection'
        expect(find_field('Start Date', disabled: true).value).to eq '01 Sep 2017 00:00'
        expect(find_field('End Date', disabled: true).value).to eq '25 Oct 2018 00:00'
        expect(find_field('Collection Term', disabled: true).value).to eq 'STANDING'
        expect(find_field('Collection Type', disabled: true).value).to eq 'ACTIVE'
        expect(find_field('Assignee', disabled: true).value).to eq 'ACC TEST'
        expect(find_field('Collection Guidance', disabled: true).value).to eq 'CR Description/Special Instructions'

        click_on 'Target'
        expect(find_field('Country', disabled: true).value).to eq 'Andorra'
        expect(find_field('Region', disabled: true).value).to eq 'GR5 - GR CODE 5'
        expect(page.find('#coordinates').value).to eq ''

        within '[data-aid=target-0]' do
          expect(page).to have_content 'Target #1'
          expect(find_field('Target Name', disabled: true).value).to eq 'SDFAASDFSADFA'
          expect(find_field('Target Type', disabled: true).value).to eq 'POINT'
          expect(find_field('Target Coordinates (DSA and Area (Min. 3 vertices); LOC Coords (Min. of 2))', disabled: true).value).to eq '39.7122594 -104.986599'
        end

        click_on 'Sensors'
        within '[data-aid=sensor-0]' do
          expect(find_field('Sensor Type', disabled: true).value).to eq ''
          expect(find_field('Sensor', disabled: true).value).to eq 'GLOBALHAWK BLK 40'
          expect(find_field('Mode', disabled: true).value).to eq '251'
          expect(find_field('Required Quality (NIIRS)', disabled: true).value).to eq '5'
          expect(find_field('Desired Quality (NIIRS)', disabled: true).value).to eq '5'
        end
      end
    end

    it 'shows prism records in the sidebar as read-only' do
      load_requests_tab
      find('.clickable', text: 'ACC 01 EO-IR-SAR RECON 1:2 20160921', match: :first).click

      within '.sidebar' do
        sleep sidebar_animation_wait_time
        expect(page).not_to have_css('.form-field--select')
        expect(page).to have_content('PRISM')
        expect(page).to have_content('ACC 01 EO-IR-SAR RECON 1:2 20160921')

        # buttons for ADD/SAVE are missing (except for Add Link)
        expect(page).not_to have_content('SAVE')
        expect(page).not_to have_content('CANCEL')
        expect(page).to have_content('Add a Link')

        # top-level fields all disabled
        expect(find_field('Priority', disabled: true).value)
        expect(find_field('Status', disabled: true).value)
        expect(find_field('Justification', disabled: true, placeholder: '').value)
        expect(find_field('Priority', disabled: true).value)
        expect(find_field('Previous Research/Background', disabled: true).value)

        # 'About' section disabled
        click_on 'About'
        expect(find_field('Requirement Type', disabled: true).value).to have_content('')
        expect(find_field('CRM Sub-Workflow', disabled: true).value).to have_content('')
        expect(find_field('Classification', disabled: true).value).to have_content('')
        expect(find_field('Custom Classification', disabled: true).value)
        expect(find_field('Caveat', disabled: true).value).to have_content('')
        expect(find_field('Submitting Organization', disabled: true).value).to have_content('')
        expect(find_field('POC(s)', disabled: true, placeholder: '').value)
        expect(find_field('NIPF Code', disabled: true).value).to have_content('')
        expect(find_field('PIR Name', disabled: true).value).to have_content('')
        expect(find_field('CENTCOM ISR Role', disabled: true).value).to have_content('')

        # 'Collection' section disabled
        click_on 'Collection'
        expect(page).not_to have_selector('#img_collectionStartDate')
        expect(page).not_to have_selector('#img_collectionEndDate')
        expect(find_field('Start Date', disabled: true).value)
        expect(find_field('End Date', disabled: true).value)
        expect(find_field('Collection Term', disabled: true).value)
        expect(find_field('Collection Type', disabled: true).value)
        expect(find_field('Assigned Team', disabled: true).value)
        expect(find_field('Assignee', disabled: true).value)
        expect(find_field('Collection Guidance', disabled: true).value).to have_content('')
        expect(find_field('EEI 1', disabled: true, placeholder: '').value)

        # 'Target' section disabled
        click_on 'Target'
        expect(page).not_to have_content('ADD TARGET')
        expect(find_field('Country', disabled: true).value)
        expect(find_field('Region', disabled: true).value)
        within '[data-aid=target-0]' do
          expect(find_field('Target Name', disabled: true).value)
          expect(find_field('Target Type', disabled: true).value).to have_content('')
          expect(find_field('Target Coordinates (DSA and Area (Min. 3 vertices); LOC Coords (Min. of 2))',
                            disabled: true).value)
        end

        # 'Sensors' section disabled
        click_on 'Sensors'
        expect(page).not_to have_content('ADD SENSOR')
        within '[data-aid=sensor-0]' do
          expect(find_field('Sensor Type', disabled: true).value)
          expect(find_field('Sensor', disabled: true).value)
          expect(find_field('Mode', disabled: true).value)
          expect(find_field('Required Quality (NIIRS)', disabled: true).value)
          expect(find_field('Desired Quality (NIIRS)', disabled: true).value)
        end
      end
    end
  end

  it 'add comments to a Prism record' do
    load_requests_tab
    find('.clickable', text: 'ACC 01 EO-IR-SAR RECON 1:2 20160921', match: :first).click

    within '.sidebar' do
      sleep sidebar_animation_wait_time
      click_on 'Activity'

      fill_in 'addComment', with: 'This is a comment comment'
      click_on 'Post'
      expect(page).to have_content('This is a comment')
      click_on 'Details'
      expect(page).not_to have_css('.form-field--select')
    end

    visit '/'
    click_tab 'view_list'
    find('.clickable', text: 'ACC 01 EO-IR-SAR RECON 1:2 20160921', match: :first).click
    within '.sidebar' do
      sleep sidebar_animation_wait_time
      click_on 'Activity'
      expect(page).to have_content('This is a comment')
    end
  end

  it 'can add and delete a comment to an existing rfi' do
    load_requests_tab

    find('.clickable', text: 'ACC 01 EO-IR-SAR RECON 1:2 20160921', match: :first).click

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
