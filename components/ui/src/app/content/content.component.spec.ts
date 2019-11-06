import {ComponentFixture, TestBed} from '@angular/core/testing'
import {AgmCoreModule} from '@agm/core'
import {Store, StoreModule} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {navigationReducer} from '../state/reducers'
import {ChangeTabAction} from '../state/actions'
import {By} from '@angular/platform-browser'
import {ContentComponent} from './content.component'
import {Component, Input} from '@angular/core'

@Component({
    selector: 'stargate-map',
    template: '<div>the map!</div>'
})
class FakeMapComponent {
    @Input()
    activeTab: string
}

@Component({
    selector: 'stargate-rfi',
    template: '<div>the rifs!</div>',
})
class FakeRfiComponent {

}

describe('ContentComponent', () => {
    let component: ContentComponent
    let store: Store<AppState>
    let fixture: ComponentFixture<ContentComponent>

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [ContentComponent, FakeMapComponent, FakeRfiComponent],
            imports: [AgmCoreModule.forRoot(),
                StoreModule.forRoot({
                    nav: navigationReducer
                })
            ]
        })

        fixture = TestBed.createComponent(ContentComponent)
        component = fixture.componentInstance
        store = TestBed.get(Store)
    })

    it('watches for changes to the current nav', () => {
        fixture.detectChanges()
        expect(fixture.debugElement.query(By.directive(FakeMapComponent)).nativeElement.hasAttribute('hidden')).toBeFalsy()
        expect(fixture.debugElement.query(By.directive(FakeRfiComponent)).nativeElement.hasAttribute('hidden')).toBeTruthy()

        store.dispatch(new ChangeTabAction('LIST'))
        fixture.detectChanges()
        expect(fixture.debugElement.query(By.directive(FakeMapComponent)).nativeElement.hasAttribute('hidden')).toBeTruthy()
        expect(fixture.debugElement.query(By.directive(FakeRfiComponent)).nativeElement.hasAttribute('hidden')).toBeFalsy()
    })
})
