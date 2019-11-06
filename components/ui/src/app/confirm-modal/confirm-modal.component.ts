import {Component} from '@angular/core'
import {Store} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {ConfirmationReplyAction, ShowConfirmModalAction} from '../state/actions'
import {ConfirmationAnswer, ConfirmationReply} from '../models/confirmation-reply.model'

@Component({
    selector: 'stargate-confirm-modal',
    templateUrl: './confirm-modal.component.html'
})
export class ConfirmModalComponent {

    show = false
    val: ConfirmationReply

    constructor(private store: Store<AppState>) {
        this.store.select('showConfirmationModal').subscribe( (val: ConfirmationReply) => {
            this.show = !!val
            this.val = val
        })

        this.store.select('confirmationReply').subscribe( (val:string) => {
            if (val) {
                this.store.dispatch(new ConfirmationReplyAction(undefined))
            }
        })
    }

    ok() {
        this.val.answer = ConfirmationAnswer.OK
        this.store.dispatch(new ConfirmationReplyAction(this.val))
        this.store.dispatch(new ShowConfirmModalAction(undefined))
    }

    cancel() {
        this.val.answer = ConfirmationAnswer.CANCEL
        this.store.dispatch(new ConfirmationReplyAction(this.val))
        this.store.dispatch(new ShowConfirmModalAction(undefined))
    }
}
