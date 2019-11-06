import {ComponentFixture, TestBed} from '@angular/core/testing'
import {SidebarTargetComponent} from './sidebar-target.component'
import {CoordinatesService} from '../../services/coordinates.service'
import {FormArray, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms'
import {
    confirmationReplyReducer,
    prismNominationsReducer,
    rfiListReducer,
    showConfirmationModalReducer
} from '../../state/reducers'
import {Store, StoreModule} from '@ngrx/store'
import {makeRfi} from '../../../test_helpers/utilities'
import {AppState} from '../../state/appstate'
import {MockRecordService} from '../../../test_helpers/mockRecordService'
import {ClickOutsideModule} from 'ng-click-outside'
import {RecordService} from '../../services/record.service'
import {TargetPopoverComponent} from '../target-popover/target-popover.component'
import {RecordFormCreatorService} from "../record-form-creator/record-form-creator.service";
import {ResizingTextareaComponent} from "../../common/resizing-textarea/resizing-textarea.component"
import {AddButtonComponent} from "../../common/add-button/add-button.component";
import {RecordItem} from "../../models/record.model";

describe('Sidebar Target', function () {
    let component: SidebarTargetComponent
    let fixture: ComponentFixture<SidebarTargetComponent>
    let store: Store<AppState>
    let mockCoordinatesService: any
    let mockRecordService: MockRecordService

    beforeEach(() => {
        mockCoordinatesService = jasmine.createSpyObj<CoordinatesService>('coordinatesService', ['getLngLat', 'isValidFormat'])
        mockCoordinatesService.getLngLat.and.returnValue([-140.2345, 30.2345])
        mockCoordinatesService.isValidFormat.and.returnValue(true)
        mockRecordService = new MockRecordService()

        TestBed.configureTestingModule({
            declarations: [
                SidebarTargetComponent,
                TargetPopoverComponent,
                ResizingTextareaComponent,
                AddButtonComponent
            ],
            providers: [
                {provide: CoordinatesService, useValue: mockCoordinatesService},
                {provide: RecordService, useValue: mockRecordService},
            ],
            imports: [
                ClickOutsideModule,
                FormsModule,
                ReactiveFormsModule,
                StoreModule.forRoot({
                    rfis: rfiListReducer,
                    prismNominations: prismNominationsReducer,
                    confirmationReply: confirmationReplyReducer,
                    showConfirmationModal: showConfirmationModalReducer
                }),
            ],
        })

        fixture = TestBed.createComponent(SidebarTargetComponent)
        store = TestBed.get(Store)
        component = fixture.componentInstance
    })

    describe('adds and deletes a target', () => {
        it('to a new rfi', () => {
            component.rfiForm = new RecordFormCreatorService().createRecordForm({} as RecordItem)
            component.addTarget()
            const targets = component.rfiForm.get('targets') as FormArray
            const target1 = targets.controls[0] as FormGroup
            const target2 = targets.controls[1] as FormGroup
            expect(target1.controls['rfiId'].value).toEqual('')
            expect(target2.controls['rfiId'].value).toEqual('')

            spyOn(mockRecordService, 'deleteRecordTarget')
            component.deleteTarget(1)
            const targets2 = component.rfiForm.get('targets') as FormArray
            expect(targets2.length).toEqual(1)
            expect(mockRecordService.deleteRecordTarget).not.toHaveBeenCalled()
        })

        it('to an existing rfi', () => {
            let rfi = makeRfi({id: 1})
            component.rfiId = 1
            component.rfiForm = new RecordFormCreatorService().createRecordForm(rfi)
            component.addTarget()

            const targets = component.rfiForm.get('targets') as FormArray
            const target1 = targets.controls[0] as FormGroup
            const target2 = targets.controls[1] as FormGroup
            expect(target1.controls['rfiId'].value).toEqual(1)
            expect(target2.controls['rfiId'].value).toEqual(1)

            spyOn(mockRecordService, 'deleteRecordTarget')
            component.deleteTarget(1)
            const targets2 = component.rfiForm.get('targets') as FormArray
            expect(targets2.length).toEqual(1)
            expect(mockRecordService.deleteRecordTarget).toHaveBeenCalled()
        })
    })
})