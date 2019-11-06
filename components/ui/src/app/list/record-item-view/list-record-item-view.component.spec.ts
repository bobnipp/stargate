import {makeRfi} from '../../../test_helpers/utilities'
import {OpenEditRfiFormAction} from '../../state/actions'
import {MockRfiService} from '../../../test_helpers/mockRfiService'
import {MockPrismService} from '../../../test_helpers/mockPrismService'
import {Store, StoreModule} from '@ngrx/store'
import {AppState} from '../../state/appstate'
import {ComponentFixture, TestBed} from '@angular/core/testing'
import {RfiService} from '../../services/rfi.service'
import {PrismService} from '../../services/prism.service'
import {FormsModule} from '@angular/forms'
import {activeRecordReducer} from '../../state/reducers'
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core'
import Spy = jasmine.Spy
import {ListRecordItemViewComponent} from './list-record-item-view.component'
import {ListService} from "../../services/list.service";
import {MockListService} from "../../../test_helpers/mockListService";
import {RecordType} from '../../models/record.model';


describe('ListRecordItemViewComponent', () => {

    let component: ListRecordItemViewComponent
    let fetchRfisSpy: Spy
    let fetchNominationsSpy: Spy
    let mockRfiService: MockRfiService
    let mockPrismService: MockPrismService
    let mockListService: MockListService
    let store: Store<AppState>
    let fixture: ComponentFixture<ListRecordItemViewComponent>
    let dispatchSpy: Spy

    beforeEach(async () => {
        mockRfiService = new MockRfiService()
        mockPrismService = new MockPrismService()
        mockListService = new MockListService()

        TestBed.configureTestingModule({
            declarations: [ListRecordItemViewComponent],
            providers: [
                {provide: RfiService, useValue: mockRfiService},
                {provide: PrismService, useValue: mockPrismService},
                {provide: ListService, useValue: mockListService},
            ],
            imports: [
                FormsModule,
                StoreModule.forRoot({
                    activeRecord: activeRecordReducer
                })
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        })

        store = TestBed.get(Store)
        fetchRfisSpy = spyOn(mockRfiService, 'fetchRfis').and.callThrough()
        fetchNominationsSpy = spyOn(mockPrismService, 'fetchNominations').and.callThrough()
        fixture = TestBed.createComponent(ListRecordItemViewComponent)
        dispatchSpy = spyOn(store, 'dispatch').and.callThrough()
        component = fixture.componentInstance
    })

    describe('ngOnInit', () => {
        it('sets isActive to true if record equals activeRecord ', () => {
            let rfiToOpen = makeRfi({id: 2})

            component.record = rfiToOpen
            component.ngOnInit()

            store.dispatch(new OpenEditRfiFormAction(rfiToOpen))
            expect(component.isActive).toBeTruthy()
        })

        it('sets isActive to false if record does not equal activeRecord ', () => {
            let rfiToOpen = makeRfi({id: 2})

            component.record = makeRfi({id: 1})
            component.ngOnInit()

            store.dispatch(new OpenEditRfiFormAction(rfiToOpen))
            expect(component.isActive).toBeFalsy()
        })
    })

    describe('delete', () => {
        it('calls the lists service when removing a record from the list', () => {
            spyOn(component.removeRecord, 'emit')
            component.record = makeRfi()
            component.list = {name: 'test', records: []}

            component.removeFromList()

            expect(component.removeRecord.emit).toHaveBeenCalledWith({recordId: '1', recordType: RecordType.RFI})
        })
    })
})
