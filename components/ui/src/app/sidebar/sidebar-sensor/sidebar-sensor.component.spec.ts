import {ComponentFixture, TestBed} from '@angular/core/testing'
import {FormArray, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms'
import {
    confirmationReplyReducer,
    prismNominationsReducer,
    rfiListReducer,
    showConfirmationModalReducer
} from '../../state/reducers'
import {Store, StoreModule} from '@ngrx/store'
import {AppState} from '../../state/appstate'
import {MockRecordService} from '../../../test_helpers/mockRecordService'
import {RecordService} from '../../services/record.service'
import {SidebarSensorComponent} from "./sidebar-sensor.component";
import {RecordFormCreatorService} from '../record-form-creator/record-form-creator.service';
import {RecordItem} from "../../models/record.model";
import {makeRfi} from "../../../test_helpers/utilities";
import {AddButtonComponent} from "../../common/add-button/add-button.component";
import {ClickOutsideModule} from "ng-click-outside";

describe('Sidebar Sensor', function () {
    let component: SidebarSensorComponent
    let fixture: ComponentFixture<SidebarSensorComponent>
    let store: Store<AppState>
    let mockRecordService: MockRecordService

    beforeEach(() => {
        mockRecordService = new MockRecordService()

        TestBed.configureTestingModule({
            declarations: [
                SidebarSensorComponent,
                AddButtonComponent
            ],
            providers: [
                {provide: RecordService, useValue: mockRecordService},
            ],
            imports: [
                FormsModule,
                ClickOutsideModule,
                ReactiveFormsModule,
                StoreModule.forRoot({
                    rfis: rfiListReducer,
                    prismNominations: prismNominationsReducer,
                    confirmationReply: confirmationReplyReducer,
                    showConfirmationModal: showConfirmationModalReducer
                }),
            ],
        })

        fixture = TestBed.createComponent(SidebarSensorComponent)
        store = TestBed.get(Store)
        component = fixture.componentInstance
    })

    describe('adds and deletes a sensor', () => {
        it('to a new rfi', () => {
            component.rfiForm = new RecordFormCreatorService().createRecordForm({} as RecordItem)
            component.addSensor()
            const sensors = component.rfiForm.get('sensors') as FormArray
            const sensor1 = sensors.controls[0] as FormGroup
            const sensor2 = sensors.controls[1] as FormGroup
            expect(sensor1.controls['rfiId'].value).toEqual('')
            expect(sensor2.controls['rfiId'].value).toEqual('')

            spyOn(mockRecordService, 'deleteRecordSensor')
            component.deleteSensor(1)
            const sensors2 = component.rfiForm.get('sensors') as FormArray
            expect(sensors2.length).toEqual(1)
            expect(mockRecordService.deleteRecordSensor).not.toHaveBeenCalled()
        })

        it('to an existing rfi', () => {
            let rfi = makeRfi({id: 1})
            component.rfiId = 1
            component.rfiForm = new RecordFormCreatorService().createRecordForm(rfi)
            component.addSensor()

            const sensors = component.rfiForm.get('sensors') as FormArray
            const sensor1 = sensors.controls[0] as FormGroup
            const sensor2 = sensors.controls[1] as FormGroup
            expect(sensor1.controls['rfiId'].value).toEqual(1)
            expect(sensor2.controls['rfiId'].value).toEqual(1)

            spyOn(mockRecordService, 'deleteRecordSensor')
            component.deleteSensor(1)
            const sensors2 = component.rfiForm.get('sensors') as FormArray
            expect(sensors2.length).toEqual(1)
            expect(mockRecordService.deleteRecordSensor).toHaveBeenCalled()
        })
    })
})