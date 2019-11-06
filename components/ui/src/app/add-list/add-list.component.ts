import {Component, EventEmitter, Output} from '@angular/core'

@Component({
    selector: 'stargate-add-list',
    templateUrl: './add-list.component.html',
    styleUrls: ['../sidebar/form.scss']
})
export class AddListComponent {

    @Output()
    close: EventEmitter<string> = new EventEmitter<string>()

    listName: string

    constructor() {}

    doClose() {
        this.close.emit(undefined)
    }

    createList() {
        this.close.emit(this.listName)
    }
}