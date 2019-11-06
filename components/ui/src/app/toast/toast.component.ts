import {Component} from '@angular/core'
import {Store} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {Toast, ToastType} from "../models/toast.model";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {timer} from "rxjs";

@Component({
    selector: 'stargate-toast',
    templateUrl: './toast.component.html',
    styleUrls: ['./toast.component.scss'],
    animations: [
        trigger('animateState', [
            state('show', style({
                opacity: 1
            })),
            state('default', style({
                opacity: 0
            })),
            transition('default => show', animate('1s 0s ease-out')),
            transition('show => default', animate('1s 0s ease-out'))
        ])
    ]
})
export class ToastComponent {

    type: ToastType = ToastType.SUCCESS
    message: string
    toastState: string = 'default'

    constructor(private store: Store<AppState>) {
        this.store.select('showToast').subscribe((val: Toast) => {
            if (val) {
                this.type = val.type
                this.message = val.message
                this.toastState = 'show'
                timer(3000).subscribe(() => {
                    this.toastState = 'default'
                })
            }
        })
    }
}
