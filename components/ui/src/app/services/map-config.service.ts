import {Injectable} from '@angular/core'
import {LazyMapsAPILoaderConfigLiteral} from '@agm/core'
import {HttpClient} from '@angular/common/http'
import {Store} from '@ngrx/store'
import {ReceivedConfigAction} from '../state/actions'
import {AppState} from '../state/appstate'

@Injectable()
export class MapConfigService implements LazyMapsAPILoaderConfigLiteral {
    apiKey: string

    constructor(private httpClient: HttpClient, private store: Store<AppState>) {
        this.httpClient.get('/api/v1/config/GOOGLE_MAPS_API_KEY', {responseType: 'text'})
            .subscribe((resp: string) => {
                this.apiKey = resp
                this.store.dispatch(new ReceivedConfigAction(true))
            })
    }

}
