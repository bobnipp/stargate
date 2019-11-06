import {Component} from '@angular/core'
import {Store} from '@ngrx/store'
import {AppState} from '../state/appstate'

@Component({
    selector: 'content',
    styleUrls: ['./content.component.scss'],
    templateUrl: './content.component.html'
})
export class ContentComponent {
    activeTab: string

    constructor(private store: Store<AppState>) {
        this.store.select('nav').subscribe(nav => this.activeTab = nav)
    }
}