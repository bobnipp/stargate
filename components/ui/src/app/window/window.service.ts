import {Injectable} from '@angular/core'

declare global {
    interface Window {
        MoW: any,
        map: any
    }
}

function getWindow(): any {
    return window
}

@Injectable()
export class WindowService {
    get nativeWindow(): any {
        return getWindow()
    }
}
