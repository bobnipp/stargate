import {ComponentFixture, TestBed} from '@angular/core/testing'
import {SidebarCollectionComponent} from './sidebar-collection.component'
import {FormArray, FormsModule, ReactiveFormsModule} from '@angular/forms'
import {OwlDateTimeModule} from 'ng-pick-datetime'
import {OwlMomentDateTimeModule} from 'ng-pick-datetime-moment'
import {ClickOutsideModule} from 'ng-click-outside'
import {DatePickerComponent} from '../../common/date-picker/date-picker.component'
import {RecordFormCreatorService} from "../record-form-creator/record-form-creator.service";
import {ResizingTextareaComponent} from "../../common/resizing-textarea/resizing-textarea.component"
import {AddButtonComponent} from "../../common/add-button/add-button.component";
import {RecordItem} from "../../models/record.model";
import {makeRfi} from "../../../test_helpers/utilities";

describe('SidebarCollectionComponent', () => {
    let fixture: ComponentFixture<SidebarCollectionComponent>
    let component: SidebarCollectionComponent

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [
                SidebarCollectionComponent,
                DatePickerComponent,
                ResizingTextareaComponent,
                AddButtonComponent
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


        fixture = TestBed.createComponent(SidebarCollectionComponent)
        component = fixture.componentInstance
    })

    describe('add eei button', () => {
        it('should add an empty object to the eeiFields array', () => {
            component.rfiForm = new RecordFormCreatorService().createRecordForm({} as RecordItem)
            expect(component.rfiForm.get('eeis')).not.toBeNull()
            expect((component.rfiForm.get('eeis') as FormArray).length).toBe(1)

            component.addEEI()

            expect((component.rfiForm.get('eeis') as FormArray).length).toBe(2)
        })
    })

    describe('delete', () => {
        it('should toggle menu', () => {
            expect(component.displayedMenuIndex).toEqual(-1)
            component.toggleMenu(1)
            expect(component.displayedMenuIndex).toEqual(1)
            component.toggleMenu(1)
            expect(component.displayedMenuIndex).toEqual(-1)
        })

        it('should remove eei from form and set form dirty', () => {
            component.record = makeRfi({eeis: ['one', 'two', 'three']})
            component.rfiForm = new RecordFormCreatorService().createRecordForm(component.record)

            expect((component.rfiForm.get('eeis') as FormArray).length).toEqual(3)
            component.addEEI()
            expect((component.rfiForm.get('eeis') as FormArray).length).toEqual(4)
            component.deleteEei(3)
            expect(component.rfiForm.dirty).toEqual(false)
            expect(component.displayedMenuIndex).toEqual(-1)

            component.deleteEei(2)
            expect((component.rfiForm.get('eeis') as FormArray).length).toEqual(2)
            expect(component.rfiForm.dirty).toEqual(true)
            expect(component.displayedMenuIndex).toEqual(-1)
        })

    })

})