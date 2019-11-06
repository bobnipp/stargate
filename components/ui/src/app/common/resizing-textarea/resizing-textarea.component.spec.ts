import {async, ComponentFixture, TestBed} from '@angular/core/testing'

import {ResizingTextareaComponent} from './resizing-textarea.component'
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms'
import {By} from '@angular/platform-browser'


function getTextArea(fixture: ComponentFixture<any>) {
    return fixture.debugElement.query(By.css('textarea')).nativeElement
}

describe('ResizingTextareaComponent', () => {
    let component: ResizingTextareaComponent
    let fixture: ComponentFixture<ResizingTextareaComponent>

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ResizingTextareaComponent],
            imports: [ReactiveFormsModule, FormsModule]
        }).compileComponents()
    }))

    beforeEach(() => {
        fixture = TestBed.createComponent(ResizingTextareaComponent)
        component = fixture.componentInstance
        component.formGroup = new FormGroup(
            {testControl: new FormControl()}
        )
        component.textAreaControlName = 'testControl'
    })

    it('should default the text area height to the provided minimum rows', () => {
        component.minRows = 5
        fixture.detectChanges()

        expect(getTextArea(fixture).rows).toEqual(5)
    })

    it('should not allow for more than 7 rows', () => {
        component.minRows = 1
        fixture.detectChanges()
        const textArea = getTextArea(fixture)
        textArea.value = new Array(10000 + 1).join('W')
        textArea.dispatchEvent(new Event('input'))
        fixture.detectChanges()
        expect(textArea.rows).toEqual(7)
    })

    describe('maxLength', () => {
        it('is set on the textarea when maxLength is passed in', () => {
            component.minRows = 1
            component.maxLength = '10'
            fixture.detectChanges()
            const textArea = getTextArea(fixture)
            expect(textArea.getAttribute('maxLength')).toEqual('10')
        })

        it('is not set on the textarea when maxLength is not passed in', () => {
            component.minRows = 1

            fixture.detectChanges()
            const textArea = getTextArea(fixture)
            expect(textArea.getAttribute('maxLength')).toBeFalsy()
        })
    })
})
