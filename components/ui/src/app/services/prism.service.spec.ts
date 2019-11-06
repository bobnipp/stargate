import {MockHttpClient} from '../../test_helpers/mockHttpClient'
import {Store, StoreModule} from '@ngrx/store'
import {TestBed} from '@angular/core/testing'
import {HttpClient} from '@angular/common/http'
import {PrismService} from './prism.service'
import {AppState} from '../state/appstate'
import {prismNominationsReducer} from '../state/reducers'
import {OpenReadOnlyRecordFormAction, ReceivedPrismNominationsAction} from '../state/actions'
import {of} from 'rxjs/internal/observable/of'
import {RecordItem, RecordType} from '../models/record.model'
import {Contracts, validateAndRespond} from '../../test_helpers/contractHelpers'
import Spy = jasmine.Spy

describe('PrismService', () => {
    let service: PrismService
    let getSpy: Spy
    let store: Store<AppState>
    let dispatchSpy: Spy

    beforeEach(() => {
        const mockHttpClient = new MockHttpClient()

        TestBed.configureTestingModule({
            providers: [
                PrismService,
                {provide: HttpClient, useValue: mockHttpClient}
            ],
            imports: [StoreModule.forRoot({prismNominations: prismNominationsReducer})]
        })

        service = TestBed.get(PrismService)
        store = TestBed.get(Store)
        const client = TestBed.get(HttpClient)
        getSpy = spyOn(client, 'get')
        dispatchSpy = spyOn(store, 'dispatch')
    })

    describe('fetchNominations', () => {
        it('loads nominations from the server and puts them in the store', () => {
            const cannedPrismNominations = [
                {
                    id: 'id1',
                    title: 'title 1',
                    status: 'A',
                    priority: 'B',
                    createdOn: '2018-1-1',
                    targets: [],
                    country: 'country',
                    city: 'city',
                    region: 'region',
                    nai: 'nai',
                    prevResearch: 'research',
                    poc: 'poc',
                    operation: 'operation'

                },
                {
                    id: 'id2',
                    title: 'title 2',
                    status: 'N',
                    priority: 'T',
                    createdOn: '2018-1-1',
                    targets: [],
                    country: 'country',
                    city: 'city',
                    region: 'region',
                    nai: 'nai',
                    prevResearch: 'research',
                    poc: 'poc',
                    operation: 'operation'
                }
            ]
            getSpy.and.callFake(validateAndRespond(Contracts.GET_PRISM_NOMINATIONS, cannedPrismNominations))

            service.fetchNominations()
            expect(getSpy).toHaveBeenCalledWith('/api/v1/prism/nominations')
            expect(dispatchSpy).toHaveBeenCalledWith(new ReceivedPrismNominationsAction(cannedPrismNominations.map(nom => nom as RecordItem)))
        })
    })

    describe('fetchNomination', () => {
        it('loads a single nomination from the server and puts it in the store', () => {
            const cannedPrismNomination = {
                id: 'id1#cool',
                title: 'title 1',
                status: 'A',
                priority: 'B',
                collectionStartDate: '2018-10-19Z',
                collectionEndDate: '2018-10-22Z'
            }
            getSpy.and.returnValue(of(cannedPrismNomination))

            service.fetchNominationById('id1#cool')

            expect(getSpy).toHaveBeenCalledWith('/api/v1/prism/nominations/id1%23cool')
            expect(dispatchSpy).toHaveBeenCalledWith(new OpenReadOnlyRecordFormAction({
                id: 'id1#cool',
                title: 'title 1',
                status: 'A',
                priority: 'B',
                recordType: RecordType.NOMINATION,
                collectionStartDate: '19 Oct 2018 00:00',
                collectionEndDate: '22 Oct 2018 00:00'
            } as RecordItem))
        })
    })
})
