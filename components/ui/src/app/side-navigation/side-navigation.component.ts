import {Component} from '@angular/core'
import {Store} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {ChangeTabAction, OpenCreateRfiFormAction} from '../state/actions'

@Component({
    selector: 'side-navigation',
    templateUrl: './side-navigation.component.html',
    styleUrls: ['./side-navigation.component.scss'],
})
export class SideNavigationComponent {
    private selectedNav: string

    constructor(private store: Store<AppState>) {
        this.store.select('nav').subscribe(selectedNav => this.selectedNav = selectedNav)
    }

    openCreateForm() {
        this.store.dispatch(new OpenCreateRfiFormAction())
    }

    onTabSelect(event: any) {
        this.store.dispatch(new ChangeTabAction(event))
    }
}