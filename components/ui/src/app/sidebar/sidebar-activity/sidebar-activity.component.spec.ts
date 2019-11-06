import {SidebarActivityComponent} from './sidebar-activity.component'
import {ComponentFixture, TestBed} from '@angular/core/testing'
import {AppState} from '../../state/appstate'
import {Store, StoreModule} from '@ngrx/store'
import {currentUserReducer} from '../../state/reducers'
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms'
import {User} from '../../models/user.model'
import {ReceivedCurrentUserAction, ShowToastAction} from '../../state/actions'
import {of} from 'rxjs/index'
import {makeNewRfi, makePrismNomination, makeRfi} from '../../../test_helpers/utilities'
import {RfiService} from '../../services/rfi.service'
import {MockRfiService} from '../../../test_helpers/mockRfiService'
import {MockRecordService} from '../../../test_helpers/mockRecordService'
import {RecordService} from '../../services/record.service'
import {FileDetails, RecordActivity} from '../../models/record-activity.model'
import {RecordFormCreatorService} from '../record-form-creator/record-form-creator.service'
import {ClickOutsideModule} from 'ng-click-outside'
import {ResizingTextareaComponent} from '../../common/resizing-textarea/resizing-textarea.component'
import {FileUploadComponent} from "../../common/file-upload/file-upload.component";
import {HttpEventType} from "@angular/common/http";
import {ToastType} from "../../models/toast.model";

describe('sidebar-activity component', () => {
    let component: SidebarActivityComponent
    let fixture: ComponentFixture<SidebarActivityComponent>
    let store: Store<AppState>
    let mockRfiService: MockRfiService
    let mockRecordService: MockRecordService

    const user = {
        id: 1,
        username: 'clevername',
        firstName: 'clever',
        lastName: 'name',
        email: 'clever@name.com',
        notes: 'this user is clever'
    } as User

    beforeEach(() => {
        mockRfiService = new MockRfiService()
        mockRecordService = new MockRecordService()

        TestBed.configureTestingModule({
            declarations: [SidebarActivityComponent, ResizingTextareaComponent, FileUploadComponent],
            providers: [
                {provide: RfiService, useValue: mockRfiService},
                {provide: RecordService, useValue: mockRecordService}
            ],
            imports: [
                StoreModule.forRoot({currentUser: currentUserReducer}),
                ReactiveFormsModule,
                FormsModule,
                ClickOutsideModule
            ]
        })

        fixture = TestBed.createComponent(SidebarActivityComponent)
        component = fixture.componentInstance
        component.currentUser = {
            username: 'steve.holt',
            id: 9,
            firstName: 'steve',
            lastName: 'holt',
            email: 'steve.holt@example.com',
            showUserDetails: true
        }
        store = TestBed.get(Store)
    })

    it('gets the current user', () => {
        store.dispatch(new ReceivedCurrentUserAction(user))
        expect(component.currentUser).toEqual(user)
    })

    describe('activities', () => {
        it('does not make external calls if new rfi', () => {
            spyOn(mockRecordService, 'saveRecordActivity')
            spyOn(mockRecordService, 'deleteRecordActivity')

            component.record = makeNewRfi()
            component.createRecordActivity()
            expect(mockRecordService.saveRecordActivity).not.toHaveBeenCalled()
            component.deleteActivity(1)
            expect(mockRecordService.deleteRecordActivity).not.toHaveBeenCalled()
        })

        it('does not dirty the form on an existing rfi', () => {
            spyOn(mockRfiService, 'saveRfi').and.returnValue(of({}))
            spyOn(mockRecordService, 'saveRecordActivity').and.returnValue(of({}))
            const rfi = makeRfi({id: 1})
            component.record = rfi
            component.recordForm = new RecordFormCreatorService().createRecordForm(rfi)
            component.activityForm = new FormGroup({
                rfiId: new FormControl(''),
                text: new FormControl('', Validators.required),
                username: new FormControl(''),
                timestamp: new FormControl(''),
            })
            const activity = {
                rfiId: 1,
                text: 'this is a comment',
                username: 'clevername',
                timestamp: 'sometimestamp',
                attachments: []
            }
            component.activityForm.patchValue(activity)

            component.createRecordActivity()

            expect(component.recordForm.dirty).toBeFalsy()
        })

        it('creates and deletes a activity for an existing rfi', () => {
            const expectedActivity: RecordActivity = {
                rfiId: 1,
                text: 'this is a comment',
                username: 'clevername',
                timestamp: 'sometimestamp',
                attachments: [{name: 'test.jpg', size: '1 kB', type: 'jpg'}]
            }
            spyOn(mockRecordService, 'saveRecordActivity').and.returnValue(of({
                type: HttpEventType.Response,
                body: expectedActivity
            }))
            spyOn(mockRecordService, 'deleteRecordActivity').and.returnValue(of({}))

            const currentDate = new Date()
            jasmine.clock().mockDate(currentDate)

            component.record = makeRfi()
            component.currentUser = user
            component.recordForm = new RecordFormCreatorService().createRecordForm(makeRfi({id: 1}))

            const activity = {
                rfiId: 1,
                text: 'this is a comment',
                username: 'clevername',
                timestamp: currentDate.getTime().toString(),
                attachments: [{
                    fileBytes: 'xxxxxx',
                    filename: 'test.txt',
                    fileSize: '5 kB'
                }]
            }

            component.ngOnInit()
            component.activityForm.patchValue(activity)

            component.addFile({bytes: 'xxxxxx', sizeString: '5 kB', size: 5000, name: 'test.txt'})

            component.createRecordActivity()

            expect(mockRecordService.saveRecordActivity).toHaveBeenCalledWith(activity)
            expect(component.record.activities[0]).toEqual(expectedActivity)

            component.deleteActivity(0)
            expect(mockRecordService.deleteRecordActivity).toHaveBeenCalled()
        })

        it('creates an activity for a PRISM record', () => {
            const expectedActivity: RecordActivity = {
                rfiId: 1,
                text: 'this is a comment',
                username: 'clevername',
                timestamp: 'sometimestamp',
                attachments: [{name: 'test.jpg', size: '1 kB', type: 'jpg'}]
            }
            spyOn(mockRecordService, 'saveRecordActivity').and.returnValue(of({
                type: HttpEventType.Response,
                body: expectedActivity
            }))
            spyOn(mockRfiService, 'fetchRfi')

            const currentDate = new Date()
            jasmine.clock().mockDate(currentDate)

            component.record = makePrismNomination()
            component.currentUser = user
            component.recordForm = new RecordFormCreatorService().createRecordForm(makePrismNomination())
            component.ngOnInit()

            const activity = {
                rfiId: 1,
                text: 'this is a comment',
                username: 'clevername',
                timestamp: currentDate.getTime().toString(),
                attachments: [{
                    fileBytes: 'xxxxxx',
                    filename: 'test.txt',
                    fileSize: '5 kB'
                }]
            }
            component.activityForm.patchValue(activity)
            component.addFile({bytes: 'xxxxxx', sizeString: '5 kB', size: 5000, name: 'test.txt'})

            component.createRecordActivity()

            expect(mockRecordService.saveRecordActivity).toHaveBeenCalledWith(activity)
            expect(mockRfiService.fetchRfi).not.toHaveBeenCalled()
            expect(component.record.activities[0]).toEqual(expectedActivity)
        })

        it('does not process the file if larger than max size', () => {
            const fileData1: FileDetails = {name: 'test15mb.txt', size: 14000000, bytes: '', sizeString: '15.1 mB'}
            const fileData2: FileDetails = {name: 'test1mb.txt', size: 1010000, bytes: '', sizeString: '1.1 mB'}
            const fileData3: FileDetails = {name: 'test900kb.txt', size: 900000, bytes: '', sizeString: '900 kB'}
            const sizeErrorSpy = spyOn(store, 'dispatch')

            component.record = makeRfi()
            component.currentUser = user
            component.recordForm = new RecordFormCreatorService().createRecordForm(makeRfi({id: 1}))

            component.ngOnInit()

            component.addFile(fileData1)
            expect(sizeErrorSpy).not.toHaveBeenCalled()

            component.addFile(fileData2)
            expect(sizeErrorSpy).toHaveBeenCalledWith(
                new ShowToastAction({
                    type: ToastType.ERROR,
                    message: `Cannot add file ${fileData2.name}: total file size for upload is greater than 15 mB`
                })
            )

            component.removeFile(0)
            component.addFile(fileData3)
            expect(sizeErrorSpy).toHaveBeenCalledTimes(1)
        })
    })

    describe('formatTimestamp', () => {
        it('formats the activity timestamp correctly', () => {
            const timeInMillis = new Date().getTime().toString()
            const expectedDateString = new Date(parseInt(timeInMillis)).toLocaleDateString('en-US',
                {month: 'short', day: 'numeric', hour12: true, hour: '2-digit', minute: '2-digit'})
            expect(component.formatTimestamp(timeInMillis)).toEqual(expectedDateString)
        })
    })
})
