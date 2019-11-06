import {ComponentFixture, TestBed} from '@angular/core/testing'
import {Store, StoreModule} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {ToastComponent} from './toast.component';
import {ToastType} from "../models/toast.model";
import {showToastReducer} from "../state/reducers";
import {ShowToastAction} from "../state/actions";

describe('ToastComponent', () => {
    let component: ToastComponent
    let fixture: ComponentFixture<ToastComponent>
    let store: Store<AppState>

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [
                ToastComponent
            ],
            imports: [
                StoreModule.forRoot({
                    showToast: showToastReducer
                })
            ],
        })

        fixture = TestBed.createComponent(ToastComponent)
        component = fixture.componentInstance
        store = TestBed.get(Store)
        jasmine.clock().install()
    })

    afterEach(() => {
        jasmine.clock().uninstall()
    })

    it('animates the component', () => {
        expect(component.toastState).toEqual('default')
        expect(component.message).toBeFalsy()
        expect(component.type).toEqual(ToastType.SUCCESS)

        store.dispatch(new ShowToastAction({type: ToastType.ERROR, message: 'This is a toast message'}))
        expect(component.message).toEqual('This is a toast message')
        expect(component.type).toEqual(ToastType.ERROR)
        expect(component.toastState).toEqual('show')

        jasmine.clock().tick(3001)

        expect(component.toastState).toEqual('default')
    })

})
