import {ComponentFixture, TestBed} from '@angular/core/testing'

import {RecordListViewComponent} from './record-list-view.component'
import {FormsModule} from '@angular/forms'
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core'
import {Store, StoreModule} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {ClickOutsideModule} from 'ng-click-outside'
import {makeRfi} from '../../test_helpers/utilities'
import {ReceivedListAction, ReceivedOpenLinksAction, ShowToastAction} from '../state/actions'
import {
    activeRecordReducer,
    customListCreatedReducer,
    prismNominationsReducer,
    receivedListReducer,
    receivedOpenLinksReducer,
    rfiListReducer
} from '../state/reducers'
import {MockListService} from '../../test_helpers/mockListService'
import {ListService} from '../services/list.service'
import {RecordItem, RecordType} from "../models/record.model"
import {CustomList} from "../models/custom-list.model";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToastType} from "../models/toast.model";
import {MockRecordService} from '../../test_helpers/mockRecordService';
import {RecordService} from "../services/record.service";
import Spy = jasmine.Spy;

describe('RecordListViewComponent', () => {
    let component: RecordListViewComponent
    let fetchListsSpy: Spy
    let deleteListSpy: Spy
    let mockRecordService: MockRecordService
    let mockListService: MockListService
    let store: Store<AppState>
    let fixture: ComponentFixture<RecordListViewComponent>
    let dispatchSpy: Spy

    beforeEach(async () => {
        mockRecordService = new MockRecordService()
        mockListService = new MockListService()

        TestBed.configureTestingModule({
            declarations: [RecordListViewComponent],
            providers: [
                {provide: RecordService, useValue: mockRecordService},
                {provide: ListService, useValue: mockListService}
            ],
            imports: [
                FormsModule,
                ClickOutsideModule,
                BrowserAnimationsModule,
                StoreModule.forRoot({
                    prismNominations: prismNominationsReducer,
                    rfis: rfiListReducer,
                    activeRecord: activeRecordReducer,
                    lists: receivedListReducer,
                    customListCreated: customListCreatedReducer,
                    receivedOpenLinks: receivedOpenLinksReducer
                })
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        })

        store = TestBed.get(Store)
        dispatchSpy = spyOn(store, 'dispatch').and.callThrough()
        fetchListsSpy = spyOn(mockListService, 'fetchLists').and.callThrough()
        deleteListSpy = spyOn(mockListService, 'deleteList').and.callThrough()
        fixture = TestBed.createComponent(RecordListViewComponent)
        component = fixture.componentInstance
    })

    describe('onLoad', () => {
        it('fetches custom lists and sorts alphabetically', () => {
            const listArray = [{name: 'zebra', records: []}, {name: 'lion', records: []}]
            const listArray2 = [{name: 'zebra', records: []}, {name: 'lion', records: []}, {name: 'list3', records: []}]

            fetchListsSpy.and.callFake(() => {
                store.dispatch(new ReceivedListAction(listArray))
            })

            component.ngOnInit()

            expect(fetchListsSpy).toHaveBeenCalled()
            expect(component.lists).toEqual(listArray)
            expect(component.showLists).toEqual([])

            component.toggleCustomList('zebra')
            expect(component.showLists).toEqual([{name: 'zebra', records: []}])

            component.isAddingList = true
            store.dispatch(new ReceivedListAction(listArray2))
            expect(component.showLists).toEqual([{name: 'zebra', records: []}, {name: 'list3', records: []}])
            expect(component.isAddingList).toEqual(false)

            component.toggleCustomList('zebra')
            expect(component.showLists).toEqual([{name: 'list3', records: []}])
        })
    })

    describe('subscriptions', () => {
        it('subscription to list sorts alphabetically', () => {
            const listArray = [{name: 'Zebra', records: []}, {name: 'apple', records: []}, {name: 'truck', records: []}]
            const alphabetically = [{name: 'apple', records: []}, {name: 'truck', records: []}, {
                name: 'Zebra',
                records: []
            }]

            store.dispatch(new ReceivedListAction(listArray))
            expect(component.lists).toEqual(alphabetically)
        })
    })

    describe('setLength', () => {
        it('sets the length of justification', () => {
            const value1 = 'Lorem Ipsum is simply dummy text of the ' +
                'printing and typesetting industry. Lorem Ipsum has been the ' +
                'industry\'s standard dummy text ever since the 1500s'

            const value1trimmed = component.getTrimmedString(value1, 90)
            expect(value1trimmed.length).toEqual(93)
            expect(value1trimmed).toEqual(`${value1.substr(0, 90)}...`)

            const value2 = 'test'
            expect(component.getTrimmedString(value2, 90)).toEqual(value2)
        })
    })

    describe('system lists', () => {
        it('toggleList sets the display value according to the list name', () => {
            expect(component.showRFIList).toBeTruthy()
            expect(component.showNOMList).toBeTruthy()

            component.toggleList(RecordType.RFI)
            expect(component.showRFIList).toBeFalsy()
            expect(component.showNOMList).toBeTruthy()

            component.toggleList(RecordType.NOMINATION)
            expect(component.showRFIList).toBeFalsy()
            expect(component.showNOMList).toBeFalsy()

            component.toggleList(RecordType.NOMINATION)
            expect(component.showRFIList).toBeFalsy()
            expect(component.showNOMList).toBeTruthy()
        })

        it('refresh calls the record service to refresh the list', () => {
            const fetchRecordsSpy = spyOn(mockRecordService, 'fetchRecords')
            component.refreshList(RecordType.RFI)
            expect(fetchRecordsSpy).toHaveBeenCalledWith(RecordType.RFI)
        })
    })

    describe('links lists', () => {
        let rfi: RecordItem
        beforeEach(async () => {
            rfi = makeRfi({
                id: 'rfi1',
                links: [
                    {
                        record1Id: 'rfi1',
                        record2Id: 'rfi2',
                        record1System: 'IMM',
                        record2System: 'IMM',
                        title: '',
                        status: ''
                    },
                    {
                        record1Id: 'rfi3',
                        record2Id: 'rfi1',
                        record1System: 'IMM',
                        record2System: 'IMM',
                        title: '',
                        status: ''
                    },
                    {
                        record1Id: 'rfi1',
                        record2Id: 'nom1',
                        record1System: 'IMM',
                        record2System: 'PRISM',
                        title: '',
                        status: ''
                    },
                    {
                        record1Id: 'nom2',
                        record2Id: 'rfi1',
                        record1System: 'PRISM',
                        record2System: 'IMM',
                        title: '',
                        status: ''
                    }
                ]
            })
        })

        it('displays', () => {
            component.linkRecords = []
            store.dispatch(new ReceivedOpenLinksAction([rfi]))

            expect(component.linkRecords).toEqual([rfi])
            expect(component.linksListAdded).toEqual(true)
        })

        it('hides', () => {
            component.linkRecords = [rfi]
            component.closeLinkList(rfi.id)
            expect(dispatchSpy).toHaveBeenCalledWith(new ReceivedOpenLinksAction([]))
        })
    })

    describe('adding list', () => {
        it('does not add if list name is empty or null', () => {
            const saveListSpy = spyOn(mockListService, 'saveList')

            expect(component.showAddList).toEqual(false)
            component.showAddList = true

            component.handleAddListClose(null)
            expect(saveListSpy).not.toHaveBeenCalled()

            component.handleAddListClose('')
            expect(saveListSpy).not.toHaveBeenCalled()

            expect(component.showAddList).toEqual(false)
        })

        it('does not add if list name is duplicate but opens duplicate list and dispatches toast', () => {
            const duplicateListName: string = 'already exists'
            const saveListSpy = spyOn(mockListService, 'saveList')

            component.showAddList = true
            component.lists = [{name: duplicateListName, records: []}]

            component.handleAddListClose(duplicateListName)

            expect(dispatchSpy).toHaveBeenCalledWith(new ShowToastAction({
                type: ToastType.INFO,
                message: `List with name '${duplicateListName}' already exists`
            }))
            expect(saveListSpy).not.toHaveBeenCalled()
            expect(component.showLists).toEqual([{name: duplicateListName, records: []}])
        })

        it('adds a list and displays it', () => {
            const listName: string = 'new list'
            const saveListSpy = spyOn(mockListService, 'saveList')

            component.showAddList = true

            component.handleAddListClose(listName)

            expect(dispatchSpy).not.toHaveBeenCalled()
            expect(saveListSpy).toHaveBeenCalledWith(listName)
        })
    })

    describe('delete list', () => {
        it('calls deleteList', () => {
            component.lists = [{name: 'some-list'}] as CustomList[]

            component.deleteList('some-list')
            expect(deleteListSpy).toHaveBeenCalledWith('some-list')

        })
    })

    describe('custom lists', () => {
        it('displays and hides', () => {
            const list = {name: 'custom list', records: []}
            component.lists = []
            component.showLists = []
            component.isAddingList = true

            store.dispatch(new ReceivedListAction([list]))
            expect(component.lists).toEqual([list])
            expect(component.showLists).toEqual([list])
            expect(component.customListAdded).toEqual(true)

            component.toggleCustomList('custom list')
            expect(component.lists).toEqual([list])
            expect(component.showLists).toEqual([])
            component.customListAdded = false

            component.toggleCustomList('custom list')
            expect(component.lists).toEqual([list])
            expect(component.showLists).toEqual([list])
            expect(component.customListAdded).toEqual(true)
        })
    })
})
