import {AfterViewInit, Component, EventEmitter, Input, Output} from '@angular/core'
import {FormGroup} from "@angular/forms"

@Component({
    selector: 'stargate-resizing-textarea',
    templateUrl: './resizing-textarea.component.html',
    styleUrls: ['./resizing-textarea.component.scss', '../../sidebar/form.scss']
})
export class ResizingTextareaComponent implements AfterViewInit {
    baseScrollHeight: number
    maxRows = 7

    // Reactive form attributes
    @Input()
    formGroup: FormGroup

    @Input()
    textAreaControlName: string

    @Input()
    textAreaId: string

    @Input()
    maxLength: string

    @Input()
    minRows = 1

    @Input()
    textAreaPlaceholder: string = ''

    @Input()
    textAreaClass: string = ''

    private fontSize: number

    constructor() {
    }

    ngAfterViewInit(): void {
        const textArea = document.getElementById(this.textAreaId)
        this.fontSize = parseFloat(window.getComputedStyle(textArea, null).getPropertyValue('font-size'))
        textArea.setAttribute('rows', this.minRows.toString())
        this.baseScrollHeight = textArea.clientHeight
        this.textAreaAdjust(textArea)
        if (this.maxLength) {
            textArea.setAttribute('maxLength', this.maxLength)
        }
    }

    textAreaAdjust(currentTarget): void {
        currentTarget.rows = this.minRows

        const additionalRowsNeeded = Math.ceil((currentTarget.scrollHeight - this.baseScrollHeight) / (this.fontSize * 1.22))
        currentTarget.rows = Math.min(Number(this.minRows) + Number(additionalRowsNeeded), this.maxRows)
    }

}
