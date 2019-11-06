require_relative '../spec_helper'
require_relative 'common_actions'

describe 'local tests', type: :feature, js: true, if: ENV['RUN_MAP_TESTS'] do

  sidebar_animation_wait_time = 1

  rfi = nil
  rfi2 = nil
  rfi3 = nil

  before(:all) do
    puts "Building frontend to include the map..."
    StargateUi.new.buildWithMap
    puts "Completed building frontend to include map."
  end

  after(:all) do
    puts "Building frontend to not include the map..."
    StargateUi.new.buildWithoutMap
    puts "Completed building frontend to not include the map."
  end

  before do
    rfi = ImmRfi.create!(
      title: 'Map RFI',
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
    Target.create!(imm_rfi_id: rfi.id, name: 'My Target', target_type: 'POINT', radius: "#{Random.rand.round(6)}", radius_unit: 'KM', coordinates: '30 -90')

    rfi2 = ImmRfi.create!(
      title: 'RFI 2',
      created_on: '2018-08-12T13:00:00.000',
      country: 'USA',
      coordinates: '20 20',
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

    rfi3 = ImmRfi.create!(
      title: 'RFI 3 No Coords',
      created_on: '2018-08-12T13:00:00.000',
      country: 'USA',
      coordinates: '',
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
  end

  def get_mow_map()
    script = <<-JS
              var mapComponent = ng.probe(document.getElementsByTagName('stargate-map')[0]).componentInstance;
              var map = mapComponent.map;
              return map;
    JS
    page.execute_script(script)
  end

  describe 'map' do
    it 'constrains list to features on the map view and highlights a record in the sidebar' do
      load_map_tab

      within '[data-aid=sidebar-map-list]' do
        records = all('.list-table_item')
        expect(records.length).to eq(5)

        within records[0] do
          expect(page).not_to have_css('.reveal-location_icon')
        end

        within records[1] do
          find('.reveal-location_icon').click
          expect(page).to have_css('.active')
        end

        find('[data-aid=constraints_checkbox]').click
        expect(all('.record-container').length).to eq(1)
      end

      script = <<-JS
          var mapComponent = ng.probe(document.getElementsByTagName('stargate-map')[0]).componentInstance;
          var map = mapComponent.map;
          var mow = mapComponent.MoW;
          map.events.publish(mow.Events.ID.MAP_CLICK, {});
          map.events.publish(mow.Events.ID.FEATURE_SELECT, {feature: map.getFeatures(map.getActiveOverlays()[0].id)[0], overlayId: map.getActiveOverlays()[0].id});
      JS
      page.execute_script(script)

      within '[data-aid=sidebar-map-list]' do
        expect(all('.record-container.active')[0]).to have_text(rfi2.title)
        expect(find('.record-child', text: rfi2.title)[:id]).to eq(rfi2.id.to_s)
      end
    end

    it 'draws shapes for all records that have coordinates and opens and closes the sidebar' do
      load_map_tab
      features_length_script = <<-JS
          var mapComponent = ng.probe(document.getElementsByTagName('stargate-map')[0]).componentInstance;
          var map = mapComponent.map;
          var mow = mapComponent.MoW;

          var overlayId = map.getOverlays().filter(o => o.name === 'IMM Overlay')[0].id
          
          var features = map.getFeatures(overlayId)
          return features.length
      JS

      features_length = page.execute_script(features_length_script)
      expect(features_length).to eq 6

      within '.map-sidebar' do
        find('.title', text: 'Map RFI').click
      end

      expect(page).to have_css('.sidebar')

      script = <<-JS
          var mapComponent = ng.probe(document.getElementsByTagName('stargate-map')[0]).componentInstance;
          var map = mapComponent.map;
          var mow = mapComponent.MoW;
          map.events.publish(mow.Events.ID.MAP_CLICK, {});
      JS
      page.execute_script(script)
      expect(page).not_to have_css('.sidebar')
    end
  end

  it 'can copy record IDs into the clipboard' do
    load_requests_tab

    within '[data-aid=rfi-list]' do
      copyButtons = all('.icon--copy')
      copyButtons[0].click
      expect(Clipboard.paste).to eq(rfi3.id.to_s)
    end
  end

  it 'can upload an attachment on an existing rfi' do
    rfi = ImmRfi.make_rfi(title: "Link Test 1", country: 'US', status: 'To Do', eeis: '["eei data 1", "eei data 2", "eei data 3"]')
    load_requests_tab

    find('#add-button').click
    within '.sidebar' do
      sleep sidebar_animation_wait_time
      expect(page).to have_button('Activity', disabled: true)
    end

    find('.list-table_item', text: rfi.title).click

    within '.sidebar' do
      sleep sidebar_animation_wait_time

      click_on 'Activity'
      fill_in 'addComment', with: 'This is a comment comment'
      puts File.dirname(__FILE__)
      attach_file('file-upload', File.dirname(__FILE__) + '/test_for_upload.txt', visible: false)
      click_on 'Post'
      expect(page).to have_content('test_for_upload.txt')
    end
  end

end