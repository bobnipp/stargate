import {Component, ElementRef, Input, OnChanges, OnInit, ViewChild} from '@angular/core'
import {FormGroup} from '@angular/forms'
import * as moment from 'moment'

@Component({
    selector: 'stargate-date-picker',
    templateUrl: './date-picker.component.html',
    styleUrls: ['./date-picker.component.scss', '../../sidebar/form.scss']
})
export class DatePickerComponent implements OnChanges {
    @Input()
    formGroup: FormGroup

    @Input()
    attributeName: string

    @Input()
    displayName: string

    @ViewChild('dateInput')
    dateInput: ElementRef

    datePickerOpen = false
    dateMask = 'DD MMM YYYY HH:mm'
    datePickerSelectedDate = new Date()
    datePickerClasses: string
    initialValue: string = ''

    private maskIndex = 0
    private ignoredChars = [' ', ':', '/']
    private validKeys = /[A-Z,0-9,a-z]/

    constructor() {
    }

    ngOnChanges() {
        this.initialValue = ''
        this.datePickerClasses = `date-time-picker ${this.attributeName}`
        const formDateValue = this.formGroup.get(this.attributeName).value
        if (formDateValue && formDateValue.length > 1) {
            this.initialValue = formDateValue
        }
    }

    toggleDatePicker() {
        this.datePickerOpen = !this.datePickerOpen
        if (!this.datePickerOpen) {
            const datePickerDateFormatted = moment(this.datePickerSelectedDate).format(this.dateMask)
            this.formGroup.get(this.attributeName).patchValue(datePickerDateFormatted, {emitEvent: false})
            this.formGroup.get(this.attributeName).markAsDirty()
            this.dateInput.nativeElement.value = datePickerDateFormatted
            this.maskIndex = this.dateMask.length
        }
    }

    closeDatePicker() {
        this.datePickerOpen = false
    }

    onFocus() {
        if (!this.dateInput.nativeElement.value) {
            this.initialValue = this.dateMask
            this.dateInput.nativeElement.value = this.dateMask
        }
    }

    preventSelect() {
        this.dateInput.nativeElement.selectionEnd = this.dateInput.nativeElement.selectionStart
    }

    handleInput(keypress) {
        if (keypress.key && keypress.key.length === 1 && keypress.key.match(this.validKeys)) {
            this.updateValue(keypress.key)
            this.dateInput.nativeElement.setSelectionRange(this.maskIndex, this.maskIndex)
            return false
        }
    }

    handleBackspace(keypress) {
        if (keypress.key && keypress.key === 'Backspace') { // <== cross-browser?
            let currentValue = this.dateInput.nativeElement.value
            if (!currentValue || currentValue.length < 1) {
                currentValue = this.dateMask
            }

            this.maskIndex = this.dateInput.nativeElement.selectionStart

            if (this.maskIndex === 1) {
                currentValue = this.dateMask.substring(0, 1) + currentValue.substring(1)
            } else if (this.maskIndex != 0) {
                currentValue = currentValue.substring(0, this.maskIndex - 1) + this.dateMask.substr(this.maskIndex - 1, 1) + currentValue.substring(this.maskIndex)
            }

            this.maskIndex = this.maskIndex > 0 ? this.maskIndex - 1 : 0
            if (this.ignoredChars.includes(this.dateMask.substr(this.maskIndex - 1, 1))) {
                this.maskIndex--
            }

            if (currentValue === this.initialValue) {
                this.formGroup.get(this.attributeName).markAsPristine()
            } else {
                this.formGroup.get(this.attributeName).markAsDirty()
            }

            this.dateInput.nativeElement.value = currentValue
            this.formGroup.get(this.attributeName).patchValue(currentValue, {emitEvent: false})
            this.dateInput.nativeElement.setSelectionRange(this.maskIndex, this.maskIndex)
            return false
        }
    }

    updateValue(keyValue) {
        let currentValue = this.dateInput.nativeElement.value
        let newDateValue: string

        if (this.dateInput.nativeElement.selectionStart < this.dateMask.length) {
            this.maskIndex = this.dateInput.nativeElement.selectionStart
        }

        if (this.ignoredChars.includes(this.dateMask.substr(this.maskIndex, 1))) {
            this.maskIndex++
        }

        if (this.maskIndex >= this.dateMask.length) {
            newDateValue = currentValue
        } else if (this.maskIndex == 0) {
            newDateValue = keyValue + currentValue.substring(1)
        } else {
            newDateValue = currentValue.substring(0, this.maskIndex) + keyValue + currentValue.substring(this.maskIndex + 1)
        }

        this.maskIndex++
        if (this.ignoredChars.includes(this.dateMask.substr(this.maskIndex, 1))) {
            this.maskIndex++
        }

        if (newDateValue && newDateValue !== currentValue) {
            this.dateInput.nativeElement.value = newDateValue
            this.formGroup.get(this.attributeName).patchValue(newDateValue, {emitEvent: false})
            this.formGroup.get(this.attributeName).markAsDirty()

            if (newDateValue === this.initialValue) {
                this.formGroup.get(this.attributeName).markAsPristine()
            }
        }
    }
}
