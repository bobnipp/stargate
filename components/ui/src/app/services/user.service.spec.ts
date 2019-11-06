import {UserService} from './user.service'
import {AppState} from '../state/appstate'
import {Store, StoreModule} from '@ngrx/store'
import {MockHttpClient} from '../../test_helpers/mockHttpClient'
import {TestBed} from '@angular/core/testing'
import {HttpClient} from '@angular/common/http'
import {currentUserReducer} from '../state/reducers'
import {User} from '../models/user.model'
import {Contracts, validateAndRespond} from '../../test_helpers/contractHelpers'
import {ReceivedCurrentUserAction} from '../state/actions'
import Spy = jasmine.Spy

describe('UserService', () => {
    let service: UserService
    let getSpy: Spy
    let dispatchSpy: Spy
    let store: Store<AppState>

    beforeEach(() => {
        const mockHttpClient = new MockHttpClient()

        TestBed.configureTestingModule({
            providers: [
                UserService,
                {provide: HttpClient, useValue: mockHttpClient}
            ],
            imports: [StoreModule.forRoot({currentUser: currentUserReducer})]
        })

        service = TestBed.get(UserService)
        store = TestBed.get(Store)
        const client = TestBed.get(HttpClient)
        getSpy = spyOn(client, 'get').and.callThrough()
        dispatchSpy = spyOn(store, 'dispatch')
    })

    describe('getCurrentUser', () => {
        it('gets the current user from the api and dispatches it to the store', () => {
            const user = {
                id: 1,
                username: 'clevername',
                firstName: 'clever',
                lastName: 'name',
                email: 'clever@name.com',
                notes: 'this user is clever'
            } as User

            getSpy.and.callFake(validateAndRespond(Contracts.GET_USER, user))

            service.getCurrentUser()
            expect(getSpy).toHaveBeenCalledWith('/api/v1/users/current')
            expect(dispatchSpy).toHaveBeenCalledWith(new ReceivedCurrentUserAction(user))
        })
    })
})