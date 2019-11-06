import {ComponentFixture, TestBed} from '@angular/core/testing'

import {Store, StoreModule} from '@ngrx/store'

import {AppState} from '../state/appstate'
import {SidebarComponent} from './sidebar.component'
import {MockRfiService} from '../../test_helpers/mockRfiService'
import {RfiService} from '../services/rfi.service'
import {
    activeRecordReducer,
    activeRfiMainFormReducer,
    activeRfiSubFormReducer,
    confirmationReplyReducer,
    recordSelectOptionsReducer,
    showConfirmationModalReducer,
} from '../state/reducers'
import {FormArray, FormsModule, ReactiveFormsModule} from '@angular/forms'
import {
    CloseSidebarAction,
    ConfirmationReplyAction,
    OpenCreateRfiFormAction,
    OpenEditRfiFormAction,
    ShowConfirmModalAction, ShowToastAction,
    ToggleRfiMainFormAction
} from '../state/actions'
import {By} from '@angular/platform-browser'
import {makeRfi} from '../../test_helpers/utilities'
import {of} from 'rxjs'
import {throwError} from 'rxjs/internal/observable/throwError'
import {OwlDateTimeModule} from 'ng-pick-datetime'
import {OwlMomentDateTimeModule} from 'ng-pick-datetime-moment'
import {SidebarAboutComponent} from './sidebar-about/sidebar-about.component'
import {SidebarCollectionComponent} from './sidebar-collection/sidebar-collection.component'
import {SidebarTargetComponent} from './sidebar-target/sidebar-target.component'
import {SidebarActivityComponent} from './sidebar-activity/sidebar-activity.component'
import {SidebarDetailsComponent} from './sidebar-details/sidebar-details.component'
import {ClickOutsideModule} from 'ng-click-outside'
import {DatePickerComponent} from '../common/date-picker/date-picker.component'
import {RecordItem, RecordType} from '../models/record.model'
import {SidebarHistoryComponent} from './sidebar-history/sidebar-history.component'
import {RecordFormCreatorService} from "./record-form-creator/record-form-creator.service"
import {MockPrismService} from "../../test_helpers/mockPrismService"
import {PrismService} from "../services/prism.service"
import {MockRecordService} from "../../test_helpers/mockRecordService"
import {RecordService} from "../services/record.service"
import {SidebarExistingLinkComponent} from "./sidebar-existing-link/sidebar-existing-link.component"
import {TargetPopoverComponent} from "./target-popover/target-popover.component"
import {CUSTOM_ELEMENTS_SCHEMA, SimpleChanges} from '@angular/core'
import {ResizingTextareaComponent} from "../common/resizing-textarea/resizing-textarea.component"
import {ListService} from "../services/list.service";
import Spy = jasmine.Spy;
import {CopyButtonComponent} from "../common/copy-button/copy-button.component";
import {CoordinateValidators} from "../validators/custom-validators";
import {ToastType} from "../models/toast.model";
import {MockListService} from "../../test_helpers/mockListService";
import {ConfirmationAnswer, makeDeleteRecordConfirmation} from '../models/confirmation-reply.model';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

describe('SidebarComponent', () => {
    let component: SidebarComponent
    let fixture: ComponentFixture<SidebarComponent>
    let store: Store<AppState>
    let mockRfiService: MockRfiService
    let mockPrismService: MockPrismService
    let mockRecordService: MockRecordService
    let mockListService: MockListService
    let dispatchSpy: Spy

    beforeEach(() => {
        mockRfiService = new MockRfiService()
        mockPrismService = new MockPrismService()
        mockRecordService = new MockRecordService()
        mockListService = new MockListService()

        TestBed.configureTestingModule({
            declarations: [
                SidebarComponent,
                SidebarAboutComponent,
                SidebarCollectionComponent,
                SidebarTargetComponent,
                SidebarActivityComponent,
                SidebarHistoryComponent,
                SidebarDetailsComponent,
                DatePickerComponent,
                SidebarExistingLinkComponent,
                TargetPopoverComponent,
                ResizingTextareaComponent,
                CopyButtonComponent
            ],
            providers: [
                {provide: RfiService, useValue: mockRfiService},
                {provide: RecordService, useValue: mockRecordService},
                {provide: PrismService, useValue: mockPrismService},
                {provide: RecordFormCreatorService, useValue: new RecordFormCreatorService()},
                {provide: ListService, useValue: mockListService}
            ],
            imports: [
                StoreModule.forRoot({
                    activeRecord: activeRecordReducer,
                    recordSelectOptions: recordSelectOptionsReducer,
                    activeRfiSubForm: activeRfiSubFormReducer,
                    activeRfiMainForm: activeRfiMainFormReducer,
                    confirmationReply: confirmationReplyReducer,
                    showConfirmationModal: showConfirmationModalReducer
                }),
                FormsModule,
                ReactiveFormsModule,
                OwlDateTimeModule,
                OwlMomentDateTimeModule,
                ClickOutsideModule,
                BrowserAnimationsModule
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        })

        fixture = TestBed.createComponent(SidebarComponent)
        component = fixture.componentInstance
        store = TestBed.get(Store)
        mockRfiService = TestBed.get(RfiService)
        dispatchSpy = spyOn(store, 'dispatch').and.callThrough()
        fixture.detectChanges()

        jasmine.clock().install()
    })

    afterEach(() => {
        jasmine.clock().uninstall()
    })

    describe('more menu', () => {
        it('is false on load', () => {
            component.ngOnInit()

            expect(component.showMoreMenu).toBeFalsy()
        });

        it('toggleMoreMenu toggles showMoreMenu', () => {
            component.ngOnInit()
            expect(component.showMoreMenu).toBeFalsy()

            component.toggleMoreMenu()
            expect(component.showMoreMenu).toBeTruthy()

            component.toggleMoreMenu()
            expect(component.showMoreMenu).toBeFalsy()
        });

        it('toggleAddToListMenu toggles showAddToListMenu', () => {
            component.ngOnInit()
            expect(component.showAddToListMenu).toBeFalsy()

            component.toggleAddToListMenu()
            expect(component.showAddToListMenu).toBeTruthy()

            component.toggleAddToListMenu()
            expect(component.showAddToListMenu).toBeFalsy()
        });
    })

    describe('form enabling and disabling', () => {
        it('disables the form when opening a PRISM nom', () => {
            component.record = {recordType: RecordType.NOMINATION} as RecordItem
            component.ngOnInit()

            jasmine.clock().tick(1)
            expect(component.recordForm.disabled).toBeTruthy()
        })

        it('enables the form when switching from a PRISM nom to an IMM record', () => {
            component.record = {recordType: RecordType.NOMINATION} as RecordItem
            component.ngOnInit()

            jasmine.clock().tick(1)
            expect(component.recordForm.disabled).toBeTruthy()

            const rfiRecord = {recordType: RecordType.RFI} as RecordItem

            const changes: SimpleChanges = {}
            changes['record'] = {previousValue: 'lol', currentValue: rfiRecord, firstChange: false, isFirstChange: () => false}
            component.ngOnChanges(changes)

            for (let controlsKey in component.recordForm.controls) {
                expect(component.recordForm.get(controlsKey).disabled).toBeFalsy()
            }
        })
    })

    describe('cancel button', () => {
        it('checks the cancel button makes the same call as the close button', () => {
            spyOn(mockRfiService, 'fetchRfiLinks').and.returnValue(of([]))
            const closeSpy = spyOn(component, 'cancel')

            store.dispatch(new OpenCreateRfiFormAction())
            fixture.detectChanges()

            component.recordForm.markAsDirty()
            const cancelButton = fixture.debugElement.query(By.css('.sidebar_cancel'))
            cancelButton.triggerEventHandler('click', {})

            expect(closeSpy).toHaveBeenCalled()
        })
    })

    describe('Resetting form values', () => {
        it('Still shows \'Select\' in the priority box upon reopening', () => {

            spyOn(mockRfiService, 'fetchRfiLinks').and.returnValue(of([]))

            store.dispatch(new OpenCreateRfiFormAction())
            fixture.detectChanges()

            expect(component.recordForm.get('priority').value).toEqual('')

            const closeButton = fixture.debugElement.query(By.css('.sidebar_action--close'))
            closeButton.triggerEventHandler('click', {})


            store.dispatch(new OpenCreateRfiFormAction())
            fixture.detectChanges()

            expect(component.recordForm.get('priority').value).toEqual('')
        })
    })

    describe('close button', () => {
        it('closes the sidebar when the form is not dirty', () => {
            spyOn(mockRfiService, 'fetchRfiLinks').and.returnValue(of([]))

            store.dispatch(new OpenEditRfiFormAction(makeRfi()))
            fixture.detectChanges()

            const closeButton = fixture.debugElement.query(By.css('.sidebar_action--close'))
            closeButton.triggerEventHandler('click', {})
            fixture.detectChanges()

            expect(dispatchSpy).toHaveBeenCalledWith(new CloseSidebarAction())
        })

        it('opens a confirm modal when the form is dirty', () => {
            spyOn(mockRfiService, 'fetchRfiLinks').and.returnValue(of([]))

            store.dispatch(new OpenCreateRfiFormAction())
            fixture.detectChanges()

            component.recordForm.markAsDirty()
            const closeButton = fixture.debugElement.query(By.css('.sidebar_action--close'))
            closeButton.triggerEventHandler('click', {})

            expect(dispatchSpy).toHaveBeenCalledWith(new ShowConfirmModalAction({owner: 'sidebar'}))
            store.dispatch(new ConfirmationReplyAction({owner: 'sidebar', answer: ConfirmationAnswer.OK}))
            fixture.detectChanges()

            expect(dispatchSpy).toHaveBeenCalledWith(new CloseSidebarAction())
        })
    })

    describe('toggling main form', () => {
        it('should dispatch an action that sets the active main form to the provided form name', () => {
            component.toggleMainForm('heythere')

            expect(dispatchSpy).toHaveBeenCalledWith(new ToggleRfiMainFormAction('heythere'))

            expect(component.activeRfiMainForm).toEqual('heythere')
        })
    })

    describe('delete', () => {
        it('pops the delete confirmation dialog', () => {
            spyOn(mockRfiService, 'fetchRfiLinks').and.returnValue(of([]))
            spyOn(mockRfiService, 'deleteRfi').and.returnValue(of({}))

            component.record = makeRfi({title: 'Delete Title'})
            fixture.detectChanges()

            component.recordForm.markAsDirty()

            expect(component.isForDelete).toEqual(false)
            component.deleteRecord()
            expect(component.isForDelete).toEqual(true)

            expect(dispatchSpy).toHaveBeenCalledWith(new ShowConfirmModalAction(makeDeleteRecordConfirmation({owner: 'sidebar'})))

            store.dispatch(new ConfirmationReplyAction({owner: 'sidebar', answer: ConfirmationAnswer.CANCEL}))
            fixture.detectChanges()

            expect(component.isForDelete).toEqual(false)

            component.deleteRecord()
            store.dispatch(new ConfirmationReplyAction({owner: 'sidebar', answer: ConfirmationAnswer.OK}))
            fixture.detectChanges()

            expect(mockRfiService.deleteRfi).toHaveBeenCalledWith(component.record)
        })
    })

    describe('save to list', () => {
        it('saves non-duplicate record', () => {
            const listSpy = spyOn(mockListService, 'saveRecord').and.returnValue(of({}))

            component.record = makeRfi()
            component.lists = [
                {
                    name: 'my list',
                    records: []
                }
            ]

            component.saveToList('my list')

            expect(listSpy).toHaveBeenCalledWith('my list', '1', RecordType.RFI)
            expect(component.showAddToListMenu).toEqual(false)
            expect(component.showMoreMenu).toEqual(false)
        })

        it('does not save duplicate record', () => {
            const listSpy = spyOn(mockListService, 'saveRecord').and.returnValue(of({}))

            component.record = makeRfi()
            component.lists = [
                {
                    name: 'my list',
                    records: [{recordId: '1', recordType: RecordType.RFI}]
                }
            ]

            component.showAddToListMenu = true
            component.showMoreMenu = true

            component.saveToList('my list')

            expect(listSpy).not.toHaveBeenCalled()
            expect(dispatchSpy).toHaveBeenCalledWith(new ShowToastAction({message: 'Already exists in my list', type: ToastType.INFO}))
            expect(component.showAddToListMenu).toEqual(true)
            expect(component.showMoreMenu).toEqual(true)
        })
    })

    describe('onSave()', () => {
        it('saves the values from the form controls', () => {
            spyOn(mockRfiService, 'fetchRfiLinks').and.returnValue(of([]))
            spyOn(mockRfiService, 'saveRfi').and.returnValue(of({}))

            component.record = makeRfi({
                title: 'The Title',
                city: 'The City',
                region: 'The Region',
                country: 'USA',
                coordinates: '39 -104',
                nai: 'The Named Area of Interest',
                priority: 'Priority 1',
                status: 'Status 1',
                justification: 'The justification',
                prevResearch: 'The research',
                requirementTypeId: 1,
                subWorkflowId: 1,
                classificationId: 1,
                caveatId: 1,
                submittingOrgId: 1,
                nipfCodeId: 1,
                pirNameId: 1,
                centcomIsrRoleId: 1,
                poc: 'The poc',
                customClassification: 'The custom classification',
                operation: 'The operation',
                collectionStartDate: '12 Aug 2019 13:00',
                collectionEndDate: '15 Nov 2019 13:00',
                collectionTerm: 'collection term',
                collectionType: 'collection type',
                assignedTeamId: 1,
                assignee: 'assignee',
                collectionGuidance: 'collection guidance',
            })
            component.ngOnInit()

            component.onSave()

            expect(mockRfiService.saveRfi).toHaveBeenCalledWith(jasmine.objectContaining({
                title: 'The Title',
                city: 'The City',
                region: 'The Region',
                country: 'USA',
                coordinates: '39 -104',
                nai: 'The Named Area of Interest',
                priority: 'Priority 1',
                status: 'Status 1',
                justification: 'The justification',
                prevResearch: 'The research',
                requirementTypeId: 1,
                subWorkflowId: 1,
                classificationId: 1,
                caveatId: 1,
                submittingOrgId: 1,
                nipfCodeId: 1,
                pirNameId: 1,
                centcomIsrRoleId: 1,
                poc: 'The poc',
                customClassification: 'The custom classification',
                operation: 'The operation',
                collectionStartDate: '12 Aug 2019 13:00',
                collectionEndDate: '15 Nov 2019 13:00',
                collectionTerm: 'collection term',
                collectionType: 'collection type',
                assignedTeamId: 1,
                assignee: 'assignee',
                collectionGuidance: 'collection guidance'
            }))
        })

        describe('successful save', () => {
            it('marks the form as pristine', () => {
                spyOn(mockRfiService, 'fetchRfiLinks').and.returnValue(of([]))
                spyOn(mockRfiService, 'saveRfi').and.returnValue(of({}))

                store.dispatch(new OpenEditRfiFormAction(makeRfi()))
                fixture.detectChanges()

                component.recordForm.controls['title'].setValue('title')
                component.recordForm.controls['priority'].setValue('priority')
                component.recordForm.controls['justification'].setValue('justification')

                component.onSave()

                expect(component.recordForm.dirty).toEqual(false)
                expect(component.recordForm.valid).toEqual(true)
            })
        })

        describe('unsuccessful save', () => {
            it('fails to save rfi and does not mark the form as pristine', () => {
                spyOn(mockRfiService, 'fetchRfiLinks').and.returnValue(of([]))
                spyOn(mockRfiService, 'saveRfi').and.returnValue(throwError({}))

                component.record = makeRfi()
                component.ngOnInit()

                component.recordForm.markAsDirty()

                component.onSave()

                expect(component.recordForm.dirty).toEqual(true)
                expect(component.recordForm.valid).toEqual(false)
            })

            it('gathers all the errors into an array', () => {
                store.dispatch(new OpenCreateRfiFormAction())

                const targets = component.recordForm.get('targets') as FormArray
                targets.controls[0].get('type').setValue('LOC')
                targets.controls[0].get('coordinates').setValue('10 10')

                component.recordForm.updateValueAndValidity()

                component.onSave()

                expect(component.errorList.length).toBe(4)
                expect(component.errorList).toContain(CoordinateValidators.INVALID_NUMBER_OF_COORDINATES_MESSAGE)
            })
        })
    })
})
