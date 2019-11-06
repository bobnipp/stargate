import {ComponentFixture, TestBed} from '@angular/core/testing'
import {Store, StoreModule} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {ChangeTabAction, OpenCreateRfiFormAction} from '../state/actions'
import {By} from '@angular/platform-browser'
import {SideNavigationComponent} from './side-navigation.component'
import {of} from 'rxjs/index'
import {MockHttpClient} from '../../test_helpers/mockHttpClient'
import {HttpClient} from '@angular/common/http'
import {CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA} from '@angular/core'
import {navigationReducer} from '../state/reducers'
import {AgmCoreModule} from '@agm/core'
import Spy = jasmine.Spy

describe('SideNavigationComponent', () => {
    let component: SideNavigationComponent
    let store: Store<AppState>
    let fixture: ComponentFixture<SideNavigationComponent>
    let getSpy: Spy

    beforeEach(() => {
        const mockHttpClient = new MockHttpClient()
        getSpy = spyOn(mockHttpClient, 'get')

        TestBed.configureTestingModule({
            declarations: [SideNavigationComponent],
            providers: [
                {provide: HttpClient, useValue: mockHttpClient},
            ],
            imports: [AgmCoreModule.forRoot(),
                StoreModule.forRoot({
                    nav: navigationReducer,
                })
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
        })

        store = TestBed.get(Store)
        fixture = TestBed.createComponent(SideNavigationComponent)
        component = fixture.componentInstance
    })

    it('should open the create rfi form', () => {
        const ryan = {
            firstName: `Ryan ${Math.random()}`,
            lastName: 'Schwarzenegger',
            email: 'yay@yay.com',
            notes: 'notes'
        }
        getSpy.and.returnValue(of(ryan))

        const dispatchSpy = spyOn(store, 'dispatch').and.callThrough()

        fixture.debugElement.query(By.css('.create')).triggerEventHandler('click', {})

        expect(dispatchSpy).toHaveBeenCalledWith(new OpenCreateRfiFormAction())
    })

    it('watches for changes to the current nav', () => {
        fixture.detectChanges()

        let select = fixture.debugElement.query(By.css('.active'))
        expect(select.nativeElement.textContent).toEqual('map')

        store.dispatch(new ChangeTabAction('LIST'))
        fixture.detectChanges()

        select = fixture.debugElement.query(By.css('.active'))
        expect(select.nativeElement.textContent).toEqual('view_list')
    })

    it('puts data into ngrx on tab click', () => {

        const dispatchSpy = spyOn(store, 'dispatch').and.callThrough()

        component.onTabSelect('blah')

        expect(dispatchSpy).toHaveBeenCalledWith(new ChangeTabAction('blah'))
    })
})
