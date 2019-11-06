import {ComponentFixture, TestBed} from '@angular/core/testing'
import {SidebarDetailsComponent} from './sidebar-details.component'
import {SidebarAboutComponent} from '../sidebar-about/sidebar-about.component'
import {SidebarCollectionComponent} from '../sidebar-collection/sidebar-collection.component'
import {SidebarTargetComponent} from '../sidebar-target/sidebar-target.component'
import {RfiService} from '../../services/rfi.service'
import {AppState} from '../../state/appstate'
import {Store, StoreModule} from '@ngrx/store'
import {MockRfiService} from '../../../test_helpers/mockRfiService'
import {activeRfiSubFormReducer, recordSelectOptionsReducer} from '../../state/reducers'
import {FormGroup, FormsModule, ReactiveFormsModule, FormControl} from '@angular/forms'
import {OwlMomentDateTimeModule} from 'ng-pick-datetime-moment'
import {OwlDateTimeModule} from 'ng-pick-datetime'
import {ReceivedSelectOptions, ToggleRfiInfoSectionAction} from '../../state/actions'
import {SidebarLinksComponent} from '../sidebar-links/sidebar-links.component'
import {ClickOutsideModule} from 'ng-click-outside'
import {DatePickerComponent} from '../../common/date-picker/date-picker.component'
import {MockPrismService} from '../../../test_helpers/mockPrismService'
import {PrismService} from '../../services/prism.service'
import {SystemSelectOptions} from '../../models/select-option.model'
import {MockRecordService} from '../../../test_helpers/mockRecordService'
import {RecordService} from '../../services/record.service'
import {SidebarExistingLinkComponent} from '../sidebar-existing-link/sidebar-existing-link.component'
import {TargetPopoverComponent} from '../target-popover/target-popover.component'
import {ResizingTextareaComponent} from "../../common/resizing-textarea/resizing-textarea.component"
import {RecordFormCreatorService} from "../record-form-creator/record-form-creator.service";
import Spy = jasmine.Spy;
import {makePrismNomination, makeRfi} from "../../../test_helpers/utilities";
import {AddButtonComponent} from "../../common/add-button/add-button.component";
import {MockRecordFormCreatorService} from "../../../test_helpers/mockRecordFormCreatorService";
import {SidebarSensorComponent} from "../sidebar-sensor/sidebar-sensor.component";
import {RecordType} from "../../models/record.model";

describe('SidebarDetailsComponent', () => {
    let component: SidebarDetailsComponent
    let fixture: ComponentFixture<SidebarDetailsComponent>
    let store: Store<AppState>
    let mockRfiService: MockRfiService
    let mockRecordService: MockRecordService
    let mockPrismService: MockPrismService
    let mockRecordFormCreatorService: MockRecordFormCreatorService
    let dispatchSpy: Spy

    beforeEach(() => {
        mockRfiService = new MockRfiService()
        mockPrismService = new MockPrismService()

        mockRecordFormCreatorService = new MockRecordFormCreatorService()

        TestBed.configureTestingModule({
            declarations: [
                SidebarDetailsComponent,
                SidebarAboutComponent,
                SidebarCollectionComponent,
                SidebarTargetComponent,
                SidebarLinksComponent,
                DatePickerComponent,
                SidebarExistingLinkComponent,
                TargetPopoverComponent,
                ResizingTextareaComponent,
                AddButtonComponent,
                SidebarSensorComponent
            ],
            providers: [
                {provide: RfiService, useValue: mockRfiService},
                {provide: RecordService, useValue: mockRecordService},
                {provide: PrismService, useValue: mockPrismService},
                {provide: RecordFormCreatorService, useValue: mockRecordFormCreatorService}
            ],
            imports: [
                StoreModule.forRoot({
                    recordSelectOptions: recordSelectOptionsReducer,
                    activeRfiSubForm: activeRfiSubFormReducer
                }),
                FormsModule,
                ReactiveFormsModule,
                OwlDateTimeModule,
                OwlMomentDateTimeModule,
                ClickOutsideModule
            ]
        })

        fixture = TestBed.createComponent(SidebarDetailsComponent)
        component = fixture.componentInstance
        store = TestBed.get(Store)
        mockRfiService = TestBed.get(RfiService)
        dispatchSpy = spyOn(store, 'dispatch').and.callThrough()

        spyOn(mockRecordFormCreatorService, 'createLinkForm').and.
        returnValue(new FormGroup(
            {
                'record2Id': new FormControl(''),
                'record2System': new FormControl('')
            }
        ))

        component.record = makeRfi()
        component.rfiForm = new RecordFormCreatorService().createRecordForm(component.record)

        fixture.detectChanges()
    })

    it('collapses and expands details', () => {
        expect(component.showDetails).toEqual(true)
        component.toggleDetailsView()
        expect(component.showDetails).toEqual(false)
        component.toggleDetailsView()
        expect(component.showDetails).toEqual(true)
    })

    describe('drop downs', () => {
        let dispatchSelectOptions: SystemSelectOptions[]

        beforeEach(() => {
            const expectedRequirementTypeOptions = [{id: 1, value: 'Request Type 1'}]
            const expectedSubWorkflowOptions = [{id: 1, value: 'CRM Sub-Workflow 1'}]
            const expectedClassificationOptions = [{id: 1, value: 'Classification 1'}]
            const expectedCaveatOptions = [{id: 1, value: 'Caveat 1'}]
            const expectedSubmittingOrgOptions = [{id: 1, value: 'Submitting Org 1'}]
            const expectedNipfCodeOptions = [{id: 1, value: 'NIPF Code 1'}]
            const expectedPirNameOptions = [{id: 1, value: 'Commander PIR 1'}]
            const expectedCentcomIsrRoleOptions = [{id: 1, value: 'ISR Role 1'}]
            const expectedAssignedTeamOptions = [{id: 1, value: 'Assigned Team 1'}]
            const expectedStatusOptions = [{id: 1, value: 'TO_DO'}]
            const expectedPriorityOptions = [{id: 1, value: 'LOW'}]

            dispatchSelectOptions = [
                {
                    type: RecordType.RFI,
                    data: {
                        requirementTypeOptions: expectedRequirementTypeOptions,
                        subWorkflowOptions: expectedSubWorkflowOptions,
                        classificationOptions: expectedClassificationOptions,
                        caveatOptions: expectedCaveatOptions,
                        submittingOrgOptions: expectedSubmittingOrgOptions,
                        nipfCodeOptions: expectedNipfCodeOptions,
                        pirNameOptions: expectedPirNameOptions,
                        centcomIsrRoleOptions: expectedCentcomIsrRoleOptions,
                        assignedTeamOptions: expectedAssignedTeamOptions,
                        statusOptions: expectedStatusOptions,
                        priorityOptions: expectedPriorityOptions
                    }
                },
                {
                    type: RecordType.NOMINATION,
                    data: {
                        requirementTypeOptions: expectedRequirementTypeOptions,
                        subWorkflowOptions: expectedSubWorkflowOptions,
                        classificationOptions: expectedClassificationOptions,
                        caveatOptions: expectedCaveatOptions,
                        submittingOrgOptions: expectedSubmittingOrgOptions,
                        nipfCodeOptions: expectedNipfCodeOptions,
                        pirNameOptions: expectedPirNameOptions,
                        centcomIsrRoleOptions: expectedCentcomIsrRoleOptions,
                        assignedTeamOptions: expectedAssignedTeamOptions,
                        statusOptions: expectedStatusOptions,
                        priorityOptions: expectedPriorityOptions
                    }
                }
            ]
        })

        it('assigns the rfi options', () => {
            component.record = makeRfi()
            store.dispatch(new ReceivedSelectOptions(dispatchSelectOptions))
            expect(component.recordSelectOptions).toEqual(dispatchSelectOptions[0].data)
        })

        it('assigns the prism options', () => {
            component.record = makePrismNomination()
            dispatchSelectOptions[1].data.priorityOptions = [{id: 1, value: 'LOW'}, {id: 1, value: 'HIGH'}]
            store.dispatch(new ReceivedSelectOptions(dispatchSelectOptions))
            expect(component.recordSelectOptions).toEqual(dispatchSelectOptions[1].data)
        })

        it('assigns the rfi options when no record type is set (important for creating a new request)', () => {
            store.dispatch(new ReceivedSelectOptions(dispatchSelectOptions))
            expect(component.recordSelectOptions).toEqual(dispatchSelectOptions[0].data)
        })
    })

    describe('toggling sub form', () => {
        it('should dispatch an action that sets active form to the provided form name', () => {
            component.toggle('whatevs')

            expect(dispatchSpy).toHaveBeenCalledWith(new ToggleRfiInfoSectionAction('whatevs'))

            expect(component.activeRfiSubForm).toEqual('whatevs')
        })
    })
})