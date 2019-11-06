import Spy = jasmine.Spy
import {defaultPrismNominationProperties, defaultRfiListProperties, makeRfi} from '../../../test_helpers/utilities'
import {MockRfiService} from '../../../test_helpers/mockRfiService'
import {MockPrismService} from '../../../test_helpers/mockPrismService'
import {ComponentFixture, TestBed} from '@angular/core/testing'
import {RfiService} from '../../services/rfi.service'
import {PrismService} from '../../services/prism.service'
import {FormsModule} from '@angular/forms'
import {Store, StoreModule} from '@ngrx/store'
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core'
import {AppState} from '../../state/appstate'
import {BaseRecordItemViewComponent} from './base-record-item-view.component'
import {
    CloseSidebarAction,
    ConfirmationReplyAction,
    ReceivedOpenLinksAction,
    ShowConfirmModalAction, ShowDndRecordDrops,
    ShowToastAction
} from '../../state/actions'
import {activeRecordReducer, confirmationReplyReducer} from '../../state/reducers'
import {ListService} from "../../services/list.service";
import {RecordItem, RecordType} from '../../models/record.model';
import {MockListService} from "../../../test_helpers/mockListService";
import {of} from "rxjs";
import {ToastType} from '../../models/toast.model';
import {ConfirmationAnswer, makeDeleteRecordConfirmation} from "../../models/confirmation-reply.model";

describe('BaseRecordItemViewComponent', () => {
    let component: BaseRecordItemViewComponent
    let fetchRfisSpy: Spy
    let fetchNominationsSpy: Spy
    let mockRfiService: MockRfiService
    let mockPrismService: MockPrismService
    let mockListService: MockListService
    let store: Store<AppState>
    let fixture: ComponentFixture<BaseRecordItemViewComponent>
    let dispatchSpy: Spy

    beforeEach(async () => {
        mockRfiService = new MockRfiService()
        mockPrismService = new MockPrismService()
        mockListService = new MockListService()

        TestBed.configureTestingModule({
            declarations: [BaseRecordItemViewComponent],
            providers: [
                {provide: RfiService, useValue: mockRfiService},
                {provide: PrismService, useValue: mockPrismService},
                {provide: ListService, useValue: mockListService},
            ],
            imports: [
                FormsModule,
                StoreModule.forRoot({
                    activeRecord: activeRecordReducer,
                    confirmationReply: confirmationReplyReducer
                })
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        })

        store = TestBed.get(Store)
        fetchRfisSpy = spyOn(mockRfiService, 'fetchRfis').and.callThrough()
        fetchNominationsSpy = spyOn(mockPrismService, 'fetchNominations').and.callThrough()
        fixture = TestBed.createComponent(BaseRecordItemViewComponent)
        dispatchSpy = spyOn(store, 'dispatch').and.callThrough()
        component = fixture.componentInstance
    })

    it('sets the reveal links boolean', () => {
        expect(component.showRevealLinks).toEqual(false)

        const recordWithCoords = makeRfi({coordinates: '34 -105'})
        component.record = recordWithCoords
        component.ngOnInit()
        expect(component.showRevealLinks).toEqual(true)

        const recordWithTarget = makeRfi({ targets: [{ rfiId: 1, name: 'target', type: 'LOC', coordinates: '34 -105' }]})
        component.record = recordWithTarget
        component.ngOnInit()
        expect(component.showRevealLinks).toEqual(true)

        const noCoordsRecord = makeRfi({coordinates: '', targets: [{ rfiId: 1, name: '', type: '', coordinates: '' }]})
        component.record = noCoordsRecord
        component.ngOnInit()
        expect(component.showRevealLinks).toEqual(false)
    })

    describe('active record', () => {
        it('fetches an rfi if setting the active record to an rfi', () => {
            const fetchRfiSpy = spyOn(mockRfiService, 'fetchRfi')
            const rfi = defaultRfiListProperties as RecordItem

            component.openRecord(rfi)
            expect(fetchRfiSpy).toHaveBeenCalledWith(rfi.id)
        })

        it('fetches an rfi if setting the active record to an nomination', () => {
            const fetchNomSpy = spyOn(mockPrismService, 'fetchNominationById')
            const nom = defaultPrismNominationProperties as RecordItem

            component.openRecord(nom)
            expect(fetchNomSpy).toHaveBeenCalledWith(nom.id)
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
            expect(component.showAddToListMenu).toEqual(false)
            expect(component.showMoreMenu).toEqual(false)
        })
    })

    describe('doOpenLinkList', () => {
        describe('when the array of open links is updated', () => {
            it('dispatches a close sidebar action', () => {
                const record = makeRfi()

                component.doOpenLinkList(record)

                expect(dispatchSpy).toHaveBeenCalledWith(new CloseSidebarAction())
            })

            it('dispatches an action to update the open links', () => {
                const record = makeRfi()

                component.doOpenLinkList(record)
                expect(dispatchSpy).toHaveBeenCalledWith(new ReceivedOpenLinksAction([record]))
            })
            it ('adds the record to the list of open links (newest first)', () => {
                let r1 = makeRfi()
                let r2 = makeRfi()
                let r3 = makeRfi()
                r2.id = 2
                r3.id = 3
                component.openLinks = [r2, r1]
                component.doOpenLinkList(r3)
                expect(dispatchSpy).toHaveBeenCalledWith(new ReceivedOpenLinksAction([r3, r2, r1]))
            })

            it ('does not duplicate link lists already open', () => {
                let r1 = makeRfi()
                let r2 = makeRfi()
                let r3 = makeRfi()
                r2.id = 2
                r3.id = 3
                component.openLinks = [r1, r2, r3]
                component.doOpenLinkList(r3)
                expect(dispatchSpy).not.toHaveBeenCalled()
            })
        })
    })

    describe('delete', () => {
        it('calls the rfi service when deleting an rfi', () => {
            spyOn(mockRfiService, 'deleteRfi')
            component.record = makeRfi()

            expect(component.isForDelete).toEqual(false)
            component.deleteRecord()
            expect(component.isForDelete).toEqual(true)

            expect(dispatchSpy).toHaveBeenCalledWith(new ShowConfirmModalAction(makeDeleteRecordConfirmation({owner: 'list-item'})))

            store.dispatch(new ConfirmationReplyAction({owner: 'list-item', answer: ConfirmationAnswer.CANCEL}))
            fixture.detectChanges()

            expect(component.isForDelete).toEqual(false)

            component.deleteRecord()
            expect(component.isForDelete).toEqual(true)
            store.dispatch(new ConfirmationReplyAction({owner: 'list-item', answer: ConfirmationAnswer.OK}))
            fixture.detectChanges()

            expect(mockRfiService.deleteRfi).toHaveBeenCalledWith(component.record)
        })
    })

    describe('drag and drop', () => {
        it('dispatches an event to highlight lists', () => {
            const dragEvent = {
                dataTransfer: {
                    setData: () => {}
                },
                target: {
                    id: 1
                }
            }

            component.dragStart(dragEvent)
            expect(dispatchSpy).toHaveBeenCalledWith(new ShowDndRecordDrops(true))
        })

        it('dispatches an event to remove highlights from lists', () => {
            component.dragEnd({})
            expect(dispatchSpy).toHaveBeenCalledWith(new ShowDndRecordDrops(false))
        })
    })
})