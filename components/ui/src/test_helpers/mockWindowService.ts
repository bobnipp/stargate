import {Injectable} from '@angular/core'

declare global {
    interface Window {
        MoW: any,
        map: any
    }
}

@Injectable()
export class MockWindowService {
    get nativeWindow(): any {
        return {
            MoW: {}
        }
    }
}