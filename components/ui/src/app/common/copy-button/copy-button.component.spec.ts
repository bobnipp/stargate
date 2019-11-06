import {async, ComponentFixture, TestBed} from '@angular/core/testing'

import {CopyButtonComponent} from './copy-button.component'

describe('CopyButtonComponent', () => {
    let component: CopyButtonComponent
    let fixture: ComponentFixture<CopyButtonComponent>

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [CopyButtonComponent]
        })
            .compileComponents()
    }))

    beforeEach(() => {
        fixture = TestBed.createComponent(CopyButtonComponent)
        component = fixture.componentInstance
        fixture.detectChanges()
    })

    it('should create', () => {
        expect(component).toBeTruthy()
    })

    describe('copyText', () => {
        it('creates an invisible input and populates it with the textToCopy', () => {

        })
        it('selects and copies the invisible input', () => {

        })
        it('removes the input when copy is complete', () => {

        })
    })
})
