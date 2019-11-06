import {Component, EventEmitter, Input, Output} from "@angular/core";

@Component({
    selector: 'target-popover',
    templateUrl: './target-popover.component.html',
    styleUrls: ['/target-popover.component.scss']
})
export class TargetPopoverComponent {
    @Input()
    showPopoverMid: boolean

    @Input()
    showPopoverRight: boolean

    @Input()
    icon: string

    @Output()
    clearPopover: EventEmitter<any> = new EventEmitter<any>()

    closePopover() {
        this.showPopoverRight=false
        this.showPopoverMid=false

        this.clearPopover.emit()
    }
}