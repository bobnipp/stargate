import {Component, Input, OnInit} from '@angular/core'
import {User} from '../../models/user.model'
import {AppState} from '../../state/appstate'
import {Store} from '@ngrx/store'
import {FormArray, FormControl, FormGroup, Validators} from '@angular/forms'
import {RfiService} from '../../services/rfi.service'
import {RecordItem} from '../../models/record.model'
import {RecordService} from '../../services/record.service'
import {HttpEventType} from "@angular/common/http";
import {FileDetails, RecordActivity} from "../../models/record-activity.model";
import {ShowToastAction} from "../../state/actions";
import {ToastType} from "../../models/toast.model";

@Component({
    selector: 'stargate-sidebar-activity',
    templateUrl: './sidebar-activity.component.html',
    styleUrls: ['../sidebar.component.scss', '../form.scss', 'sidebar-activity.component.scss']
})
export class SidebarActivityComponent implements OnInit {

    @Input()
    record: RecordItem

    @Input()
    recordForm: FormGroup

    currentUser: User
    activityForm: FormGroup
    showMenu: number | string

    isFileLoading: boolean = false

    progress: number = 0
    uploadSizes: number[] = []

    private maxFileSizeBytes: number = 15000000

    constructor(private store: Store<AppState>, private rfiService: RfiService, private recordService: RecordService) {
        this.store.select('currentUser').subscribe((currentUser: User) => {
            this.currentUser = currentUser
        })
    }

    ngOnInit(): void {
        this.activityForm = this.cleanActivityForm()
    }

    createRecordActivity() {
        if (this.record.id != undefined) {
            this.activityForm.get('timestamp').setValue(new Date().getTime().toString())
            this.progress = 1
            this.recordService.saveRecordActivity(this.activityForm.value).subscribe((event) => {
                if (event.type === HttpEventType.UploadProgress) {
                    const currentProgress = Math.round((100 * event.loaded) / event.total)
                    this.progress = currentProgress > 0 ? currentProgress : 1
                } else if (event.type === HttpEventType.Response) {
                    this.record.activities = [event.body as RecordActivity, ...this.record.activities]
                    this.progress = 0
                }
            }, (error) => {
                this.store.dispatch(new ShowToastAction({type: ToastType.ERROR, message: 'Comment save failed'}))
                this.progress = 0
            })
        }

        this.activityForm = this.cleanActivityForm()
    }

    fileLoading(isFileLoading) {
        this.isFileLoading = isFileLoading
    }

    addFile(fileData: FileDetails) {
        if (fileData.size + this.getUploadSize() > this.maxFileSizeBytes) {
            this.store.dispatch(new ShowToastAction({
                type: ToastType.ERROR,
                message: `Cannot add file ${fileData.name}: total file size for upload is greater than ${this.maxFileSizeBytes / 1000000} mB`
            }))
        } else {
            this.uploadSizes.push(fileData.size)
            const attachments = this.activityForm.get('attachments') as FormArray
            attachments.push(new FormGroup({
                fileBytes: new FormControl(fileData.bytes),
                filename: new FormControl(fileData.name),
                fileSize: new FormControl(fileData.sizeString)
            }))
        }
    }

    removeFile(index) {
        const attachments = this.activityForm.get('attachments') as FormArray
        attachments.removeAt(index)
        this.uploadSizes.splice(index, 1)
    }

    download(attachment) {
        this.recordService.getAttachment(this.record.id, attachment)
    }

    formatTimestamp(timestamp: string) {
        return new Date(parseInt(timestamp)).toLocaleDateString('en-US',
            {month: 'short', day: 'numeric', hour12: true, hour: '2-digit', minute: '2-digit'})
    }

    deleteActivity(activityId) {
        if (this.record.id != undefined) {
            const activity = this.record.activities.find(activity => activity.id === activityId)
            if (this.record.id != undefined) {
                this.recordService.deleteRecordActivity(activity)
            }

            this.record.activities = this.record.activities.filter(activity => activity.id !== activityId)
            this.showMenu = null
        }
    }

    openMenu(index: number) {
        this.showMenu = index
    }

    closeMenu() {
        this.showMenu = null
    }

    private getUploadSize(): number {
        let uploadSize = 0
        this.uploadSizes.forEach(size => uploadSize += size)
        return uploadSize
    }

    private cleanActivityForm(): FormGroup {
        this.uploadSizes = []
        return new FormGroup({
            rfiId: new FormControl(this.record.id),
            text: new FormControl('', Validators.required),
            username: new FormControl(this.currentUser.username),
            timestamp: new FormControl(''),
            attachments: new FormArray([])
        })
    }

}
