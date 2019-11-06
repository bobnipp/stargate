import {ComponentFixture, TestBed} from '@angular/core/testing'
import {FormsModule, ReactiveFormsModule} from '@angular/forms'
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core'
import {AddListComponent} from './add-list.component'
import Spy = jasmine.Spy;

describe('AddListComponent', () => {
    let component: AddListComponent
    let fixture: ComponentFixture<AddListComponent>
    let emitSpy: Spy

    beforeEach(async () => {
        TestBed.configureTestingModule({
            declarations: [AddListComponent],
            imports: [
                FormsModule,
                ReactiveFormsModule
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        })

        fixture = TestBed.createComponent(AddListComponent)
        component = fixture.componentInstance
        emitSpy = spyOn(component.close, 'emit')
    })

    describe('closes', () => {
        it('with a value on create', () => {
            component.listName = 'somevalue'
            component.createList()

            expect(emitSpy).toHaveBeenCalledWith('somevalue')
        })

        it('without a value on cancel', () => {
            component.doClose()

            expect(emitSpy).toHaveBeenCalledWith(undefined)
        })
    })
})