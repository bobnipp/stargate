import {Component, Input} from '@angular/core'

@Component({
    selector: 'add-button',
    styleUrls: ['./add-button.component.scss'],
    templateUrl: './add-button.component.html'
})
export class AddButtonComponent {
    @Input()
    buttonText: string
}