import {TestBed} from '@angular/core/testing'
import {HttpClient} from '@angular/common/http'
import {MockHttpClient} from '../../test_helpers/mockHttpClient'
import {Store, StoreModule} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {ReceivedListAction, ShowToastAction} from '../state/actions'
import {ListService} from './list.service'
import {receivedListReducer} from '../state/reducers'
import {of} from 'rxjs'
import {RecordType} from "../models/record.model";
import Spy = jasmine.Spy;
import {CustomList} from "../models/custom-list.model";
import {ToastType} from "../models/toast.model";

describe('ListService', () => {
    let service: ListService
    let getSpy: Spy
    let putSpy: Spy
    let deleteSpy: Spy
    let postSpy: Spy
    let dispatchSpy: Spy
    let store: Store<AppState>

    beforeEach(() => {
        const mockHttpClient = new MockHttpClient()

        TestBed.configureTestingModule({
            providers: [
                ListService,
                {provide: HttpClient, useValue: mockHttpClient},
            ],
            imports: [StoreModule.forRoot({lists: receivedListReducer})],
        })

        service = TestBed.get(ListService)
        store = TestBed.get(Store)
        const client = TestBed.get(HttpClient)

        getSpy = spyOn(client, 'get').and.callThrough()
        postSpy = spyOn(client, 'post').and.callThrough()
        putSpy = spyOn(client, 'put').and.callThrough()
        deleteSpy = spyOn(client, 'delete').and.callThrough()
        dispatchSpy = spyOn(store, 'dispatch')
    })

    describe('fetchLists', () => {
        it('sends a get to the api and dispatches an action with the lists', () => {
            const listArray = [{name: 'List1', records: []}, {name: 'List2', records: []}]

            getSpy.and.returnValue(of(listArray))

            service.fetchLists()
            expect(getSpy).toHaveBeenCalledWith('/api/v1/lists')
            expect(dispatchSpy).toHaveBeenCalledWith(new ReceivedListAction(listArray))
        })
    })


    describe('saveList', () => {
        it('creates new list (POST) and fetches updated lists', () => {
            let newListName = 'Foo'
            let allLists = [{name: 'list1', records: []}, {name: 'Foo', records: []}]

            postSpy.and.returnValue(of({}))
            getSpy.and.returnValue(of(allLists))

            service.saveList(newListName)

            expect(postSpy).toHaveBeenCalledWith('/api/v1/lists', { name: newListName })
            expect(getSpy).toHaveBeenCalled()
            expect(dispatchSpy).toHaveBeenCalledWith(new ReceivedListAction(allLists))
        })
    })

    describe('saveRecord', () => {
        it('adds a new record to a list (POST) and fetches updated lists', () => {
            let allLists: CustomList[] = [{name: 'list1', records: []}]
            let newRecordId = {recordId: 'id1', recordType: '0'}

            postSpy.and.returnValue(of({}))
            getSpy.and.returnValue(of(allLists))

            service.saveRecord('list1', 'id1', RecordType.RFI)

            expect(postSpy).toHaveBeenCalledWith('/api/v1/lists/list1', newRecordId)
            expect(getSpy).toHaveBeenCalled()
            expect(dispatchSpy).toHaveBeenCalledWith(new ReceivedListAction(allLists))
            expect(dispatchSpy).toHaveBeenCalledWith(new ShowToastAction({message: `Added to list1`, type: ToastType.SUCCESS}))
        })
    })

    describe('deleteRecord', () => {
        it('removes a record from a list (POST) and fetches updated lists', () => {
            let allLists: CustomList[] = [{name: 'list1', records: []}]

            deleteSpy.and.returnValue(of({}))
            getSpy.and.returnValue(of(allLists))

            service.removeRecord('list1', 'id1', RecordType.RFI)

            expect(deleteSpy).toHaveBeenCalledWith('/api/v1/lists/list1/id1/0')
            expect(getSpy).toHaveBeenCalled()
            expect(dispatchSpy).toHaveBeenCalledWith(new ReceivedListAction(allLists))
        })
    })

    describe('updateList', () => {
        it('updates the list with a re-ordered record set', () => {
            let allLists: CustomList[] = [{name: 'list1', records: [{recordId: '1', recordType: RecordType.RFI}]}]

            putSpy.and.returnValue(of({}))
            getSpy.and.returnValue(of(allLists))

            service.updateList(allLists[0])

            expect(putSpy).toHaveBeenCalledWith('/api/v1/lists', allLists[0])
            expect(getSpy).not.toHaveBeenCalled()
            expect(dispatchSpy).not.toHaveBeenCalled()
        })
    })
})
