import {of} from 'rxjs/internal/observable/of'

export class MockHttpClient {
    get(url: string) { return of() }

    put(url: string, payload: object) { return of() }

    post(url: string, payload: object) { return of() }

    delete(url: string, payload: object) { return of() }
}
