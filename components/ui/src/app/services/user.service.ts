import {HttpClient} from '@angular/common/http'
import {AppState} from '../state/appstate'
import {Store} from '@ngrx/store'
import {User} from '../models/user.model'
import {ReceivedCurrentUserAction} from '../state/actions'
import {Injectable} from '@angular/core'

@Injectable()
export class UserService {
    basePath = '/api/v1/users'

    constructor(private httpClient: HttpClient, private store: Store<AppState>) {}

    getCurrentUser() {
        this.httpClient.get(`${this.basePath}/current`).subscribe((user: User) => {
            this.store.dispatch(new ReceivedCurrentUserAction(user))
        })
    }
}