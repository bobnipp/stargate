import {ComponentFixture, TestBed} from '@angular/core/testing'
import {FormsModule, ReactiveFormsModule} from '@angular/forms'
import {OwlDateTimeModule} from 'ng-pick-datetime'
import {OwlMomentDateTimeModule} from 'ng-pick-datetime-moment'
import {DatePickerComponent} from './date-picker.component'
import {ClickOutsideModule} from 'ng-click-outside'
import {RecordItem} from '../../models/record.model'
import {makeRfi} from '../../../test_helpers/utilities'
import * as moment from 'moment'
import {RecordFormCreatorService} from "../../sidebar/record-form-creator/record-form-creator.service";

describe('DatePickerComponent', () => {
    let fixture: ComponentFixture<DatePickerComponent>
    let component: DatePickerComponent

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [
                DatePickerComponent
            ],
            providers: [],
            imports: [
                FormsModule,
                ReactiveFormsModule,
                OwlDateTimeModule,
                OwlMomentDateTimeModule,
                ClickOutsideModule
            ]
        })


        fixture = TestBed.createComponent(DatePickerComponent)
        component = fixture.componentInstance
        component.attributeName = 'collectionStartDate'
        component.formGroup = new RecordFormCreatorService().createRecordForm({} as RecordItem)
    })

    describe('on initialization', () => {
        it('date picker classes should include the attribute name', () => {
            component.ngOnChanges()
            expect(component.datePickerClasses).toEqual(`date-time-picker ${component.attributeName}`)
        })

        it('input value should be empty with a new RFI', () => {
            component.ngOnChanges()
            expect(component.dateInput.nativeElement.value).toEqual('')
        })

        it('should fill the input value for an existing RFI', () => {
            const date = '12 Nov 2018 00:00'
            component.formGroup = new RecordFormCreatorService().createRecordForm(makeRfi({collectionStartDate: date}))
            component.ngOnChanges()

            expect(component.initialValue).toEqual(date)
        })
        it('sets an initial date', () => {
            expect(component.datePickerSelectedDate).toBeDefined()
        })

        it('does not throw an exception if the formDateValue is null', () => {
            component.formGroup.get('collectionStartDate').setValue(null)
            component.ngOnChanges()
        })
    })

    describe('on focus', () => {
        it('sets the value of the input to the mask if the current value is empty', () => {
            component.onFocus()
            expect(component.dateInput.nativeElement.value).toEqual(component.dateMask)
            expect(component.initialValue).toEqual(component.dateMask)
        })

        it('keeps the value of the input if there is a current value', () => {
            component.dateInput.nativeElement.value = '12 Nov 2018 00:00'
            component.onFocus()
            expect(component.dateInput.nativeElement.value).toEqual('12 Nov 2018 00:00')
        })
    })

    describe('on date picker is opened', () => {
        beforeEach(() => {
            component.datePickerOpen = false
            component.toggleDatePicker()
        })

        it('toggles the date picker flag', () => {
            expect(component.datePickerOpen).toBeTruthy()
        })

    })

    describe('on date picker is closed', () => {
        beforeEach(() => {
            component.datePickerOpen = true
        })

        it('toggles the date picker flag', () => {
            component.toggleDatePicker()
            expect(component.datePickerOpen).toBeFalsy()
        })

        it('sets the current date in the correct format', () => {
            component.toggleDatePicker()
            const expectedDate = moment(component.datePickerSelectedDate).format(component.dateMask)
            expect(component.dateInput.nativeElement.value).toEqual(expectedDate)
        })

        it('marks the form as dirty', () => {
            component.toggleDatePicker()
            expect(component.formGroup.get(component.attributeName).dirty).toBeTruthy()
        })

        it('updates the form with the date value', () => {
            component.toggleDatePicker()
            const expectedDate = moment(component.datePickerSelectedDate).format(component.dateMask)
            expect(component.formGroup.get(component.attributeName).value).toEqual(expectedDate)
        })
    })

    describe('on key input', () => {
        it('overwrites the mask', () => {
            component.onFocus()
            let currentValue = component.dateInput.nativeElement.value

            expect(currentValue).toEqual(component.dateMask)
            const dateToSet = '11 Nov 2018 12:52'
            for (let i = 0; i < dateToSet.length; i++) {
                let expectedValue: string
                if (i === 0) {
                    expectedValue = `${dateToSet.charAt(i)}${component.dateMask.substring(i + 1)}`
                } else {
                    expectedValue = `${component.dateInput.nativeElement.value.substring(0, i)}${dateToSet.charAt(i)}${component.dateMask.substring(i + 1)}`
                }
                component.handleInput({key: dateToSet.charAt(i)})
                expect(component.dateInput.nativeElement.value).toEqual(expectedValue)
            }
        })

        it('does not add non-accepted characters', () => {
            component.onFocus()
            component.handleInput({key: '&'})
            expect(component.dateInput.nativeElement.value).toEqual(component.dateMask)
        })

        it('limits characters to length of the date mask', () => {
            component.onFocus()
            const dateToSet = '11Nov20181252ZZZZZZZ'
            dateToSet.split('').forEach(char => {
                component.handleInput({key: char})
            })

            expect(component.dateInput.nativeElement.value).toEqual('11 Nov 2018 12:52')
        })
    })

    describe('on backspace', () => {
        it('restores the mask', () => {
            component.dateInput.nativeElement.value = '11 Nov 2018 12:52'

            for (let i = 0; i < 13; i++) {
                component.handleBackspace({key: 'Backspace'})
            }

            expect(component.dateInput.nativeElement.value).toEqual(component.dateMask)
        })
    })

})