import {ComponentFixture, TestBed} from '@angular/core/testing'
import {CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA} from '@angular/core'
import {AppComponent} from './app.component'
import {Store, StoreModule} from '@ngrx/store'
import {AppState} from './state/appstate'
import {activeRecordReducer, currentUserReducer} from './state/reducers'
import {MockUserService} from '../test_helpers/mockUserService'
import {UserService} from './services/user.service'
import {OpenCreateRfiFormAction, ReceivedCurrentUserAction} from './state/actions'
import {User} from './models/user.model'
import {MockRfiService} from '../test_helpers/mockRfiService'
import {MockPrismService} from '../test_helpers/mockPrismService'
import {RfiService} from './services/rfi.service'
import {PrismService} from './services/prism.service'
import {By} from "@angular/platform-browser"
import {SidebarComponent} from "./sidebar/sidebar.component"
import {RecordService} from "./services/record.service"
import {MockRecordService} from "../test_helpers/mockRecordService"
import {RecordFormCreatorService} from "./sidebar/record-form-creator/record-form-creator.service"
import {ListService} from "./services/list.service"
import Spy = jasmine.Spy
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'

describe('AppComponent', () => {
    let component: AppComponent
    let store: Store<AppState>
    let getCurrentUserSpy: Spy
    let fetchRfisSpy: Spy
    let fetchNominationsSpy: Spy
    let fixture: ComponentFixture<AppComponent>

    beforeEach(() => {
        const mockUserService = new MockUserService()
        const mockRfiService = new MockRfiService()
        const mockPrismService = new MockPrismService()
        const mockRecordService = new MockRecordService()

        fetchRfisSpy = spyOn(mockRfiService, 'fetchRfis')
        fetchNominationsSpy = spyOn(mockPrismService, 'fetchNominations').and.callThrough()

        TestBed.configureTestingModule({
            declarations: [AppComponent, SidebarComponent],
            providers: [
                {provide: UserService, useValue: mockUserService},
                {provide: RfiService, useValue: mockRfiService},
                {provide: PrismService, useValue: mockPrismService},
                {provide: RecordService, useValue: mockRecordService},
                {provide: RecordFormCreatorService, useValue: new RecordFormCreatorService()},
                {provide: ListService, useValue: undefined}
            ],
            imports: [
                BrowserAnimationsModule,
                StoreModule.forRoot({
                activeRecord: activeRecordReducer,
                currentUser: currentUserReducer
            })],
            schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],
        })

        store = TestBed.get(Store)
        getCurrentUserSpy = spyOn(mockUserService, 'getCurrentUser').and.callThrough()
        fixture = TestBed.createComponent(AppComponent)
        component = fixture.componentInstance
    })

    describe('component initialization', () => {
        it('should load the current user from the server', () => {
            const ryan = {
                id: 1,
                username: 'rschwarz',
                firstName: `Ryan ${Math.random()}`,
                lastName: 'Schwarzenegger',
                email: 'yay@yay.com',
                notes: 'notes'
            } as User

            getCurrentUserSpy.and.callFake(() => {
                store.dispatch(new ReceivedCurrentUserAction(ryan))
            })

            component.ngOnInit()
            expect(getCurrentUserSpy).toHaveBeenCalled()
            expect(component.user).toEqual(ryan)
        })

        it('fetches rfis', () => {
            component.ngOnInit()
            expect(fetchRfisSpy).toHaveBeenCalled()
        })

        it('fetches requirements', () => {
            component.ngOnInit()
            expect(fetchRfisSpy).toHaveBeenCalled()
        })
    })

    describe('Sidebar component', () => {

        it('starts closed', () => {
            let sidebar = fixture.debugElement.query(By.css('.sidebar'))
            expect(sidebar).toBeFalsy()
        })

        it('can be opened', () => {
            store.dispatch(new OpenCreateRfiFormAction())
            fixture.detectChanges()
            const sidebar = fixture.debugElement.query(By.css('.sidebar'))
            expect(sidebar).toBeTruthy()
        })
    })
})
