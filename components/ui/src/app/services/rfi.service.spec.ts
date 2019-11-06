import {TestBed} from '@angular/core/testing'
import {RfiService} from './rfi.service'
import {HttpClient} from '@angular/common/http'
import {MockHttpClient} from '../../test_helpers/mockHttpClient'
import {of} from 'rxjs/internal/observable/of'
import {defaultRfiListProperties, makeNewRfi, makeRfi, makeRfiListItem} from '../../test_helpers/utilities'
import {Contracts, validateAndRespond} from '../../test_helpers/contractHelpers'
import {Store, StoreModule} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {rfiListReducer} from '../state/reducers'
import {CloseSidebarAction, OpenEditRfiFormAction, ReceivedRfisAction, ShowToastAction,} from '../state/actions'
import {RecordType, RecordItem} from '../models/record.model'
import Spy = jasmine.Spy
import {ListService} from './list.service';
import {PrismService} from "./prism.service";
import {ToastType} from "../models/toast.model";

describe('RfiService', () => {
    let service: RfiService
    let putSpy: Spy
    let getSpy: Spy
    let postSpy: Spy
    let deleteSpy: Spy
    let dispatchSpy: Spy
    let store: Store<AppState>

    beforeEach(() => {
        const mockHttpClient = new MockHttpClient()

        TestBed.configureTestingModule({
            providers: [
                RfiService,
                ListService,
                PrismService,
                {provide: HttpClient, useValue: mockHttpClient},
            ],
            imports: [StoreModule.forRoot({rfis: rfiListReducer})],
        })

        service = TestBed.get(RfiService)
        store = TestBed.get(Store)
        const client = TestBed.get(HttpClient)
        putSpy = spyOn(client, 'put').and.callThrough()
        getSpy = spyOn(client, 'get').and.callThrough()
        postSpy = spyOn(client, 'post').and.callThrough()
        deleteSpy = spyOn(client, 'delete').and.callThrough()
        dispatchSpy = spyOn(store, 'dispatch')
    })

    describe('fetchRfi', () => {
        it('sends a get to the api and dispatches an action with the rfi', () => {
            const cannedRfi = makeRfi({
                id: 1,
                title: 'War Never Changes',
                country: 'US',
                coordinates: '30 -140',
                priority: 'Routine',
                status: 'To Do',
                justification: 'never say never',
                prevResearch: 'say never sometimes'
            })
            getSpy.and.callFake(validateAndRespond(Contracts.GET_RFI, cannedRfi))

            service.fetchRfi(1)
            expect(getSpy).toHaveBeenCalledWith('/api/v1/imm/rfis/1')
            expect(dispatchSpy).toHaveBeenCalledWith(new OpenEditRfiFormAction(Object.assign({}, cannedRfi, {recordType: RecordType.RFI})))
        })
    })

    describe('fetchRfis', () => {
        it('sends a get to the api and dispatches an action with the list', () => {
            const cannedRfis = [defaultRfiListProperties, defaultRfiListProperties]
            getSpy.and.callFake(validateAndRespond(Contracts.LIST_RFIS, cannedRfis))

            service.fetchRfis()
            expect(getSpy).toHaveBeenCalledWith('/api/v1/imm/rfis')
            expect(dispatchSpy).toHaveBeenCalledWith(new ReceivedRfisAction([cannedRfis[0] as RecordItem, cannedRfis[1] as RecordItem]))
        })
    })

    it('deletes an rfi', () => {
        deleteSpy.and.returnValue(of(undefined))
        service.deleteRfi(makeRfi({title: 'Delete Title'}))
        expect(deleteSpy).toHaveBeenCalledWith('/api/v1/imm/rfis/1')
        expect(dispatchSpy).toHaveBeenCalledWith(new CloseSidebarAction())
        expect(dispatchSpy).toHaveBeenCalledWith(new ShowToastAction({message: 'Delete Title has been deleted', type: ToastType.SUCCESS}))
    })

    describe('saveRfi', () => {
        it('updates existing rfi (PUT)', () => {
            const cannedRfis = [makeRfiListItem(), makeRfiListItem()]
            const rfi = makeRfi({id: 1})

            putSpy.and.callFake(validateAndRespond(Contracts.UPDATE_RFI, {}))
            cannedRfis.push(makeRfiListItem(rfi))
            getSpy.and.returnValue(of(cannedRfis))

            service.saveRfi(rfi)

            expect(getSpy).toHaveBeenCalled()
            expect(putSpy).toHaveBeenCalledWith('/api/v1/imm/rfis/1', rfi)
            expect(dispatchSpy).toHaveBeenCalledWith(new ReceivedRfisAction(cannedRfis))
        })

        it('creates new rfi (POST)', () => {
            const cannedRfi = {title: 'hi', country: 'usa'} as RecordItem
            const newRfi = makeNewRfi(cannedRfi)
            const expectedRfi = makeRfi(cannedRfi)
            postSpy.and.callFake(validateAndRespond(Contracts.CREATE_RFI, expectedRfi))

            const rfis = [makeRfiListItem(cannedRfi), makeRfiListItem()]
            getSpy.and.returnValue(of(rfis))

            service.saveRfi(newRfi)
            expect(postSpy).toHaveBeenCalledWith('/api/v1/imm/rfis', newRfi)
            expect(getSpy).toHaveBeenCalled()
            expect(dispatchSpy).toHaveBeenCalledWith(new ReceivedRfisAction(rfis))
        })
    })
})
