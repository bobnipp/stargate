import {of} from 'rxjs/internal/observable/of'
import {MapConfigService} from './map-config.service'
import {MockHttpClient} from '../../test_helpers/mockHttpClient'
import {HttpClient} from '@angular/common/http'
import {TestBed} from '@angular/core/testing'
import {AgmCoreModule} from '@agm/core'
import {Store, StoreModule} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {mapConfigReducer} from '../state/reducers'
import {ReceivedConfigAction} from '../state/actions'
import Spy = jasmine.Spy;

describe('MapConfigService', () => {
    let getSpy: Spy
    let store: Store<AppState>
    const mockHttpClient = new MockHttpClient()

    let mapConfigService: MapConfigService

    beforeEach(() => {
        getSpy = spyOn(mockHttpClient, 'get')

        TestBed.configureTestingModule({
            imports: [AgmCoreModule.forRoot(),
                StoreModule.forRoot({
                    map: mapConfigReducer
                })
            ]
        })
        store = TestBed.get(Store)
    })

    it('should return api key', () => {
        const data = 'google maps api key'
        getSpy.and.returnValue(of(data))

        mapConfigService = new MapConfigService(mockHttpClient as HttpClient, store)
        expect(getSpy).toHaveBeenCalledWith('/api/v1/config/GOOGLE_MAPS_API_KEY', {responseType: 'text'})
        expect(mapConfigService.apiKey).toEqual(data)
    })

    it('should dispatch an action that says the key was received', () => {
        const data = 'google maps api key'
        getSpy.and.returnValue(of(data))

        const dispatchSpy = spyOn(store, 'dispatch').and.callThrough()
        mapConfigService = new MapConfigService(mockHttpClient as HttpClient, store)

        expect(dispatchSpy).toHaveBeenCalledWith(new ReceivedConfigAction(true))
    })
})
