import {ComponentFixture, TestBed} from '@angular/core/testing'

import {Store, StoreModule} from '@ngrx/store'

import {AppState} from '../state/appstate'
import {ConfirmModalComponent} from './confirm-modal.component'
import {confirmationReplyReducer, showConfirmationModalReducer,} from '../state/reducers'
import {ConfirmationReplyAction, ShowConfirmModalAction} from '../state/actions'
import Spy = jasmine.Spy
import {ConfirmationAnswer} from '../models/confirmation-reply.model';

describe('ConfirmationModalComponent', () => {
    let component: ConfirmModalComponent
    let fixture: ComponentFixture<ConfirmModalComponent>
    let store: Store<AppState>
    let dispatchSpy: Spy

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [
                ConfirmModalComponent
            ],
            imports: [
                StoreModule.forRoot({
                    showConfirmationModal: showConfirmationModalReducer,
                    confirmationReply: confirmationReplyReducer
                })
            ],
        })

        fixture = TestBed.createComponent(ConfirmModalComponent)
        component = fixture.componentInstance
        store = TestBed.get(Store)
        dispatchSpy = spyOn(store, 'dispatch').and.callThrough()
        fixture.detectChanges()
    })

    it('shows and hides the modal with "ok"', () => {
        store.dispatch(new ShowConfirmModalAction({ owner: 'modal'}))
        expect(component.show).toBeTruthy()

        component.ok()
        expect(component.show).toBeFalsy()

        expect(dispatchSpy).toHaveBeenCalledWith(new ConfirmationReplyAction({ owner: 'modal', answer: ConfirmationAnswer.OK}))
    })

    it('shows and hides the modal with "cancel"', () => {
        store.dispatch(new ShowConfirmModalAction({ owner: 'modal'}))
        expect(component.show).toBeTruthy()

        component.cancel()
        expect(component.show).toBeFalsy()

        expect(dispatchSpy).toHaveBeenCalledWith(new ConfirmationReplyAction({ owner: 'modal', answer: ConfirmationAnswer.CANCEL }))
    })

})
