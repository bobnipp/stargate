import {ComponentFixture, TestBed} from '@angular/core/testing'
import {SidebarLinksComponent} from './sidebar-links.component'
import {MockRfiService} from '../../../test_helpers/mockRfiService'
import {RfiService} from '../../services/rfi.service'
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms'
import {of} from 'rxjs'
import {RecordType} from '../../models/record.model'
import {makeRfi, makeRfiListItem} from '../../../test_helpers/utilities'
import {Store, StoreModule} from '@ngrx/store'
import {
    confirmationReplyReducer,
    prismNominationsReducer,
    rfiListReducer,
    showConfirmationModalReducer
} from '../../state/reducers'
import {AppState} from '../../state/appstate'
import {ConfirmationReplyAction, ReceivedRfisAction} from '../../state/actions'
import {MockPrismService} from '../../../test_helpers/mockPrismService'
import {PrismService} from '../../services/prism.service'
import {RecordService} from '../../services/record.service'
import {MockRecordService} from '../../../test_helpers/mockRecordService'
import {SidebarExistingLinkComponent} from '../sidebar-existing-link/sidebar-existing-link.component'
import {ClickOutsideModule} from 'ng-click-outside'
import {RecordFormCreatorService} from "../record-form-creator/record-form-creator.service";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import SpyObj = jasmine.SpyObj;
import {AddButtonComponent} from "../../common/add-button/add-button.component";
import {ConfirmationAnswer} from '../../models/confirmation-reply.model';

describe('SidebarLinksComponent', () => {
    let component: SidebarLinksComponent
    let fixture: ComponentFixture<SidebarLinksComponent>
    let store: Store<AppState>
    let mockRfiService: MockRfiService
    let mockPrismService: MockPrismService
    let mockRecordService: MockRecordService
    let mockRecordFormCreatorService: SpyObj<RecordFormCreatorService>

    beforeEach(() => {
        mockRfiService = new MockRfiService()
        mockRecordService = new MockRecordService()
        mockPrismService = new MockPrismService()

        mockRecordFormCreatorService = jasmine.createSpyObj<RecordFormCreatorService>('recordFormCreatorService', ['createLinkForm'])

        let formGroup = new FormGroup({
            record1Id: new FormControl(''),
            record2Id: new FormControl(''),
            record1System: new FormControl(''),
            record2System: new FormControl('')
        })
        mockRecordFormCreatorService.createLinkForm.and.callFake((link) => {
            formGroup.patchValue(link)
            return formGroup
        })

        TestBed.configureTestingModule({
            declarations: [
                SidebarLinksComponent,
                SidebarExistingLinkComponent,
                AddButtonComponent
            ],
            providers: [
                {provide: RfiService, useValue: mockRfiService},
                {provide: RecordService, useValue: mockRecordService},
                {provide: PrismService, useValue: mockPrismService},
                {provide: RecordFormCreatorService, useValue: mockRecordFormCreatorService}
            ],
            imports: [
                ClickOutsideModule,
                StoreModule.forRoot({
                    rfis: rfiListReducer,
                    prismNominations: prismNominationsReducer,
                    confirmationReply: confirmationReplyReducer,
                    showConfirmationModal: showConfirmationModalReducer
                }),
                FormsModule,
                ReactiveFormsModule,
                NoopAnimationsModule
            ],
        })

        fixture = TestBed.createComponent(SidebarLinksComponent)
        store = TestBed.get(Store)
        component = fixture.componentInstance
        component.inputLinks = []
        component.links = []
        mockRfiService = TestBed.get(RfiService)
        mockPrismService = TestBed.get(PrismService)
    })

    describe('toggles links', () => {

        it('collapses and expands details', () => {
            expect(component.showLinks).toEqual(true)
            component.toggleLinksView()
            expect(component.showLinks).toEqual(false)
            component.toggleLinksView()
            expect(component.showLinks).toEqual(true)
        })

        it('toggle showInputFields', () => {
            component.ngOnInit()
            component.linkForm.patchValue({record1Id: '1', record1System: 'IMM', record2System: 'IMM', record2Id: '2'})
            component.showInputs()
            expect(component.showInputFields).toEqual(true)
            component.onAdd()
            expect(component.showInputFields).toEqual(false)
            component.showInputs()
            expect(component.showInputFields).toEqual(true)
        })
    })

    describe('opens link attached object', () => {
        it('opens in the sidebar if rfi is not dirty (on record1)', () => {
            spyOn(mockRfiService, 'fetchRfi')
            component.rfiId = 1
            component.openLinkedObject({record1Id: '1', record2Id: '1234', record1System: 'IMM', record2System: 'IMM'})
            expect(mockRfiService.fetchRfi).toHaveBeenCalledWith('1234')
        })

        it('opens in the sidebar if rfi is not dirty (on record2)', () => {
            spyOn(mockRfiService, 'fetchRfi')
            component.rfiId = 1234
            component.openLinkedObject({record1Id: '1', record2Id: '1234', record1System: 'IMM', record2System: 'IMM'})
            expect(mockRfiService.fetchRfi).toHaveBeenCalledWith('1')
        })

        it('opens in sidebar if rfi is dirty but the changes have been abandoned', () => {
            component.rfiId = 1
            spyOn(mockRfiService, 'fetchRfi')
            component.openLinkedObject({record1Id: '1', record2Id: '1234', record1System: 'IMM', record2System: 'IMM'})

            store.dispatch(new ConfirmationReplyAction({owner: 'links', answer: ConfirmationAnswer.OK}))
            fixture.detectChanges()
            expect(mockRfiService.fetchRfi).toHaveBeenCalledWith('1234')
        })

        it('opens a PRISM nom in the sidebar', () => {
            spyOn(mockPrismService, 'fetchNominationById')
            component.rfiId = 1
            component.openLinkedObject({
                record1Id: '1',
                record2Id: '1234',
                record1System: 'IMM',
                record2System: 'PRISM'
            })
            expect(mockPrismService.fetchNominationById).toHaveBeenCalledWith('1234')
        })
    })

    describe('record 2 id state', () => {
        it('is enabled when a record2System is specified', () => {
            component.ngOnInit()

            expect(component.linkForm.get('record2Id').enabled).toBeFalsy()

            component.linkForm.get('record2System').setValue('PRISM')
            expect(component.linkForm.get('record2Id').enabled).toBeTruthy()

            component.linkForm.get('record2System').setValue('')
            expect(component.linkForm.get('record2Id').enabled).toBeFalsy()
        })
    })

    describe('adds a link', () => {

        it('saves and deletes the link', () => {
            const rfis = [makeRfiListItem({id: 1, title: 'title1', status: 'TO DO'}), makeRfiListItem({
                id: 2,
                title: 'title2',
                status: 'WORKING'
            })]
            store.dispatch(new ReceivedRfisAction(rfis))
            const expectedRfiLink = {
                record1Id: '2',
                record2Id: '1',
                record1System: 'IMM',
                record2System: 'IMM',
                title: 'title1',
                status: 'TO DO'
            }
            const saveLinkSpy = spyOn(mockRecordService, 'saveRecordLink').and.returnValue(of({}))
            spyOn(mockRfiService, 'fetchRfi')
            spyOn(mockRecordService, 'deleteRecordLink')
            const rfi = makeRfi({id: 2})
            component.recordType = RecordType.RFI
            component.rfiId = rfi.id
            rfi.links.push(expectedRfiLink)
            const pendingRfiLink = {record1Id: '2', record2Id: '1', record1System: 'IMM', record2System: 'IMM'}
            component.ngOnInit()
            component.linkForm.patchValue(pendingRfiLink)

            component.onAdd()

            expect(saveLinkSpy).toHaveBeenCalledWith(pendingRfiLink)

            component.deleteLink(0)
            expect(mockRecordService.deleteRecordLink).toHaveBeenCalledWith(expectedRfiLink)
        })

        describe('linking PRISM noms', () => {
            it('saves and deletes the link to the api', () => {
                const rfis = [makeRfiListItem({id: 11, title: 'title1', status: 'TO DO'})]
                store.dispatch(new ReceivedRfisAction(rfis))
                spyOn(mockRecordService, 'saveRecordLink')
                spyOn(mockRecordService, 'deleteRecordLink')
                component.rfiId = '12345'
                component.recordType = RecordType.NOMINATION
                const expectedLink = {
                    record1Id: '12345',
                    record2Id: '11',
                    record1System: 'PRISM',
                    record2System: 'IMM',
                    title: 'title1',
                    status: 'TO DO'
                }
                const pendingLink = {record1Id: '12345', record1System: 'PRISM', record2Id: '11', record2System: 'IMM'}
                component.ngOnInit()
                component.linkForm.patchValue(pendingLink)

                component.onAdd()

                expect(mockRecordService.saveRecordLink).toHaveBeenCalledWith(pendingLink)

                component.deleteLink(0)
                expect(mockRecordService.deleteRecordLink).toHaveBeenCalledWith(expectedLink)
            })
        })

        it('resets the pendingRfiLink', () => {
            spyOn(mockRfiService, 'saveRfi').and.returnValue(of({}))
            component.rfiId = 12
            component.recordType = RecordType.RFI
            component.ngOnInit()
            component.linkForm.patchValue({record1Id: '12', record1System: 'IMM', record2Id: '12345', record2System: 'IMM'})
            component.onAdd()
            expect(component.linkForm.value).toEqual({
                record1Id: '12',
                record1System: 'IMM',
                record2System: ''
            })
            expect(component.linkForm.get('record2Id').disabled).toBeTruthy()
        })

        it('does not allow duplicate links', () => {
            spyOn(mockRfiService, 'saveRfi').and.returnValue(of({}))

            // Add an initial link
            const link1 = {record1Id: '12', record1System: 'IMM', record2Id: '12345', record2System: 'IMM'}
            component.rfiId = 12
            component.recordType = RecordType.RFI
            component.ngOnInit()
            component.linkForm.patchValue(link1)
            component.rfis = [makeRfi({id: '12345', title: 'some title', status: 'DONE'})]
            component.onAdd()
            expect(component.links.length).toEqual(1)

            fixture.detectChanges()
            // Re-add the same link
            const link2 = {record1Id: '12', record1System: 'IMM', record2Id: '12345', record2System: 'IMM'}
            component.rfiId = 12
            component.recordType = RecordType.RFI
            component.linkForm.patchValue(link2)
            component.onAdd()
            expect(component.links.length).toEqual(1)
            expect(component.links[0]).toEqual({
                record1Id: '12',
                record1System: 'IMM',
                record2Id: '12345',
                record2System: 'IMM',
                title: 'some title',
                status: 'DONE'
            })
        })
    })

    describe('initializing pending link', () => {
        it('initializes IMM', () => {
            component.recordType = RecordType.RFI
            component.rfiId = 13
            component.initializePendingLink()

            expect(component.linkForm.value).toEqual({
                record1Id: '13',
                record1System: 'IMM',
                record2System: ''
            })
        })

        it('initializes PRISM', () => {
            component.recordType = RecordType.NOMINATION
            component.rfiId = 'nomnomnom'
            component.initializePendingLink()

            expect(component.linkForm.value).toEqual({
                record1Id: 'nomnomnom',
                record1System: 'PRISM',
                record2System: ''
            })
        })
    })
})
