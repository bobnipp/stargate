import {ComponentFixture, TestBed} from '@angular/core/testing'
import {CustomListComponent} from "./custom-list.component";
import {ClickOutsideModule} from "ng-click-outside";
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {RecordType} from "../../models/record.model";
import {makePrismNomination, makeRfi} from "../../../test_helpers/utilities";
import {MockListService} from "../../../test_helpers/mockListService";
import {ListService} from "../../services/list.service";
import {showDndRecordDropsReducer, showToastReducer} from "../../state/reducers";
import {Store, StoreModule} from "@ngrx/store";
import {AppState} from "../../state/appstate";
import Spy = jasmine.Spy;
import {ShowDndRecordDrops, ShowToastAction} from '../../state/actions';
import {ToastType} from '../../models/toast.model';

describe('CustomListComponent', () => {
    let component: CustomListComponent
    let fixture: ComponentFixture<CustomListComponent>
    let mockListService: MockListService
    let store: Store<AppState>
    let dispatchSpy: Spy

    beforeEach(async () => {
        mockListService = new MockListService()
        TestBed.configureTestingModule({
            declarations: [CustomListComponent],
            providers: [
                {provide: ListService, useValue: mockListService}
            ],
            imports: [
                FormsModule,
                ClickOutsideModule,
                StoreModule.forRoot({
                    showToast: showToastReducer,
                    showDndRecordDrops: showDndRecordDropsReducer
                })
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        })

        fixture = TestBed.createComponent(CustomListComponent)
        component = fixture.componentInstance
        store = TestBed.get(Store)
        dispatchSpy = spyOn(store, 'dispatch').and.callThrough()
    })

    describe('getRecordsForList', () => {
        it('provides full RecordItems from IMM and PRISM', () => {
            component.list = {
                name: "My List", records: [
                    {recordId: 'rfi1', recordType: RecordType.RFI},
                    {recordId: 'rfi3', recordType: RecordType.RFI},
                    {recordId: 'nom2', recordType: RecordType.NOMINATION}
                ]
            }

            let rfi1 = makeRfi({id: 'rfi1'});
            let rfi3 = makeRfi({id: 'rfi3'});
            const rfis = [
                rfi1,
                makeRfi({id: 'rfi2'}),
                rfi3
            ]

            component.rfis = rfis

            let nom2 = makePrismNomination({id: 'nom2'});
            const noms = [
                makePrismNomination({id: 'nom1'}),
                nom2,
                makePrismNomination({id: 'nom3'}),
            ]
            component.noms = noms

            component.ngOnInit()

            expect(component.listRecords).toEqual(jasmine.arrayContaining([rfi1, rfi3, nom2]))
        })
    })

    describe('drag and drop', () => {
        it('expects to be highlighted when a drag event is started', () => {
            component.list = {name: 'test', records: []}
            component.listRecords = []
            component.rfis = [makeRfi({id: 1})]
            component.noms = [makePrismNomination({id: 1})]

            store.dispatch(new ShowDndRecordDrops(true))
            fixture.detectChanges()
            expect(component.showRecordDrops).toEqual(true)

            store.dispatch(new ShowDndRecordDrops(false))
            fixture.detectChanges()
            expect(component.showRecordDrops).toEqual(false)
        })

        it('adds a new record if record does not exist and the list is empty', () => {
            const updateListSpy = spyOn(mockListService, 'updateList').and.callThrough()
            const dragEvent: DragEvent = new DragEvent('drop', {dataTransfer: new DataTransfer()})
            spyOn(component, 'getCurrentElement').and.returnValue(undefined)
            dragEvent.dataTransfer.setData('text', '1:0')

            component.list = {name: 'test', records: []}
            component.listRecords = []
            component.rfis = component.listRecords
            component.noms = [makePrismNomination({id: 1})]

            component.drop(dragEvent)
            expect(updateListSpy).toHaveBeenCalledWith({name: 'test', records: [{recordId: '1', recordType: RecordType.RFI}]})
        })

        it('updates the list if record does not exist and the list is not empty', () => {
            const updateListSpy = spyOn(mockListService, 'updateList').and.callThrough()
            const dragEvent: DragEvent = new DragEvent('drop', {dataTransfer: new DataTransfer()})
            spyOn(component, 'getCurrentElement').and.returnValue({id: '1:0'})
            dragEvent.dataTransfer.setData('text', '2:0')

            component.list = {
                name: 'test',
                records: [{recordId: '1', recordType: RecordType.RFI}, {
                    recordId: '1',
                    recordType: RecordType.NOMINATION
                }]
            }
            component.listRecords = [makeRfi({id: 1}), makePrismNomination({id: 1})]
            component.rfis = [makeRfi({id: 1})]
            component.noms = [makePrismNomination({id: 1})]

            component.drop(dragEvent)
            expect(updateListSpy).toHaveBeenCalledWith({
                name: 'test', records: [
                    {recordId: '2', recordType: RecordType.RFI},
                    {recordId: '1', recordType: RecordType.RFI},
                    {recordId: '1', recordType: RecordType.NOMINATION}
                ]
            })
            expect(dispatchSpy).toHaveBeenCalledWith(new ShowToastAction({message: `Added to ${component.list.name}`, type: ToastType.SUCCESS}))
        })

        it('re-arranges the records based on drop location and updates the list', () => {
            const updateListSpy = spyOn(mockListService, 'updateList').and.callThrough()
            const dragEvent: DragEvent = new DragEvent('drop', {dataTransfer: new DataTransfer()})
            spyOn(component, 'getCurrentElement').and.returnValue({id: '1:0'})
            dragEvent.dataTransfer.setData('text', '1:1')

            component.list = {
                name: 'test',
                records: [{recordId: '1', recordType: RecordType.RFI}, {
                    recordId: '1',
                    recordType: RecordType.NOMINATION
                }]
            }
            component.listRecords = [makeRfi({id: 1}), makePrismNomination({id: 1})]
            component.rfis = [makeRfi({id: 1})]
            component.noms = [makePrismNomination({id: 1})]

            component.drop(dragEvent)
            expect(updateListSpy).toHaveBeenCalledWith({
                name: 'test',
                records: [{recordId: '1', recordType: RecordType.NOMINATION}, {
                    recordId: '1',
                    recordType: RecordType.RFI
                }]
            })
        })

        it('does nothing if the item is dropped on itself', () => {
            const updateListSpy = spyOn(mockListService, 'updateList').and.callThrough()
            const dragEvent: DragEvent = new DragEvent('drop', {dataTransfer: new DataTransfer()})
            spyOn(component, 'getCurrentElement').and.returnValue({id: '2:0'})
            dragEvent.dataTransfer.setData('text', '2:0')

            component.list = {
                name: 'test',
                records: [{recordId: '1', recordType: RecordType.RFI}, {recordId: '2', recordType: RecordType.RFI}]
            }
            component.rfis = [makeRfi({id: 1}), makeRfi({id: 2})]

            component.drop(dragEvent)
            expect(updateListSpy).not.toHaveBeenCalled()
        })
    })
})
