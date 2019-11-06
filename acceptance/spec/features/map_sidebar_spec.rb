require_relative '../spec_helper'
require_relative 'common_actions'

describe 'map sidebar', type: :feature, js: true do
    title1 = "Title 1"
    title2 = "Title 2"

    let(:title) {'War Never Changes'}
    let(:country) {'US'}
    let(:coordinates) {"39.00 104.00"}
    let(:city) {"city #{Random.rand}"}
    let(:region) {"region #{Random.rand}"}
    let(:nai) {"NAI #{Random.rand}"}

    let!(:rfi1) {ImmRfi.create!(title: title1, coordinates: '39.756663 -105.09217', country: "Country #{Random.rand}", status: 'To Do', priority: 'Immediate', justification: 'justification 1', created_on: '2016-01-01')}
    let!(:rfi2) {ImmRfi.create!(title: title2, status: 'To Do', priority: 'Immediate', country: "Country #{Random.rand}", justification: 'justification 2', created_on: '2017-12-01')}
    let!(:rfi3) {ImmRfi.create!(
        title: title,
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
        sub_workflow_id: 2,
        classification_id: 2,
        caveat_id: 2,
        submitting_org_id: 2,
        pir_name_id: 2,
        nipf_code_id: 2,
        centcom_isr_role_id: 2,
        custom_classification: 'vault 101',
        poc: 'The Institute',
        operation: 'Blow up the world',
        collection_start_date: '12 Aug 2018 00:00',
        collection_end_date: '15 Nov 2018 00:00',
        collection_type: 'collection type1',
        collection_term: 'collection term1',
        assigned_team_id: 2,
        assignee: 'assignee1',
        collection_guidance: 'collection guidance1',
        eeis: '["eei data 1", "eei data 2", "eei data 3", "eei data 4", "eei data 5"]')
    }

    let!(:link) {ImmLink.create!(record_1_system: 'IMM', record_1_id: rfi2.id, record_2_id: rfi1.id, record_2_system: 'IMM')}

    before do
      load_map_tab
    end

    it 'sidebar can be collapsed and opened' do
      within '[data-aid=sidebar-map-list]' do
        find('.close-sidebar').click
      end
      expect(page).to_not have_css('.map-sidebar')

      find('.open-sidebar').click
      expect(page).to have_css('.map-sidebar')
    end

    it 'sidebar has rfis and requirements sorted by descending order of creation date' do
      within '[data-aid=sidebar-map-list]' do
        records = all('.list-table_item')
        expect(records.size).to eq(5)

        expect(records[0]).to have_content('War Never Changes')
        expect(records[0]).to have_content("RFI ID ##{rfi3.id}")
        expect(records[0]).to have_content('To Do')
        expect(records[0]).to have_content('never say never')

        expect(records[1]).to have_content('THIS IS LAST ')
        expect(records[1]).to have_content('SUBMITTED')
        expect(records[1]).to have_content('Another justification')

        expect(records[2]).to have_content(title2)
        expect(records[2]).to have_content("RFI ID ##{rfi2.id}")
        expect(records[2]).to have_content('To Do')
        expect(records[2]).to have_content('justification 2')

        expect(records[3]).to have_content('ACC 01 EO-IR-SAR ')
        expect(records[3]).to have_content('APPROVE')
        expect(records[3]).to have_content('NOM Justification')

        expect(records[4]).to have_content(title1)
        expect(records[4]).to have_content("RFI ID ##{rfi1.id}")
        expect(records[4]).to have_content('To Do')
        expect(records[4]).to have_content('justification 1')
      end
    end

    it 'window event fired from View Details link shows the record details' do
      Target.create!(
          imm_rfi_id: rfi3.id,
          name: "Target Name #{Random.rand}",
          target_type: 'POINT',
          radius: 1.25,
          radius_unit: 'KM',
          coordinates: "#{Random.rand} #{Random.rand}, #{Random.rand} #{Random.rand}"
      )

      RecordHistory.create!(username: 'hardcoded', action: 'Insert', rfi_id: rfi3.id)

      script = <<-JS
          window.dispatchEvent(new CustomEvent('mapDetailsLink', { detail: { id: #{rfi3.id.to_s}, system: 'IMM' } }))
      JS
      page.execute_script(script)

      within '.sidebar' do
        expect(page).to have_content("ID ##{rfi3.id}")
      end
    end

    it 'switches to list tab when a link icon is selected' do
      within '.sidenav' do
        page.find('#map-button')[:class].include?('.active')
      end

      within '[data-aid=sidebar-map-list]' do
        links = page.all('.open-link-list')
        links[0].click
      end

      within '.sidenav' do
        page.find('#list-button')[:class].include?('.active')
      end
    end
end

