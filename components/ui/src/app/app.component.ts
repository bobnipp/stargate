import {Component, OnInit, ViewChild} from '@angular/core'
import {User} from './models/user.model'
import {AppState} from './state/appstate'
import {Store} from '@ngrx/store'
import {UserService} from './services/user.service'
import {RfiService} from './services/rfi.service'
import {PrismService} from './services/prism.service'
import {RecordItem, RecordType} from "./models/record.model";
import {SidebarComponent} from "./sidebar/sidebar.component";
import {OpenEditRfiFormAction, OpenReadOnlyRecordFormAction} from "./state/actions";
import {animateChild, query, transition, trigger} from "@angular/animations";

@Component({
    selector: 'stargate-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss'],
    animations: [
        trigger('slideInOut', [
            transition(':leave', [
                query('.sidebar', [
                    animateChild()
                ])
            ])
        ])
    ]
})
export class AppComponent implements OnInit {
    user: User
    currentRecord: RecordItem = undefined
    nextRecord: RecordItem = undefined

    @ViewChild(SidebarComponent)
    recordDetails: SidebarComponent

    constructor(private store: Store<AppState>,
                private userService: UserService,
                private rfiService: RfiService,
                private prismService: PrismService) {
        this.store.select('currentUser').subscribe(user => {
            this.user = user
        })
    }

    ngOnInit() {
        this.store.select('activeRecord').subscribe(incomingRecord => {
            if (this.nextRecord === incomingRecord) {
                this.currentRecord = incomingRecord
                this.nextRecord = undefined
            } else if (this.currentRecord && incomingRecord) {
                this.nextRecord = incomingRecord
                this.recordDetails.cancel()
            } else if (!this.currentRecord && incomingRecord) {
                this.currentRecord = incomingRecord
                this.nextRecord = undefined
            } else if (!incomingRecord) {
                this.currentRecord = undefined
                if (this.nextRecord) {
                    setTimeout(() => {
                        if (this.nextRecord.recordType === RecordType.RFI) {
                            this.store.dispatch(new OpenEditRfiFormAction(this.nextRecord))
                        } else if (this.nextRecord.recordType === RecordType.NOMINATION) {
                            this.store.dispatch(new OpenReadOnlyRecordFormAction(this.nextRecord))
                        }
                    }, 1)
                }
            }
        })
        this.userService.getCurrentUser()
        this.rfiService.fetchRfis()
        this.prismService.fetchNominations()
    }
}
