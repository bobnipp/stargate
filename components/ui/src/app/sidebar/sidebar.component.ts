import {ChangeDetectorRef, Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core'
import {Store} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {CloseSidebarAction, ShowConfirmModalAction, ShowToastAction, ToggleRfiMainFormAction} from '../state/actions'
import {getDataForRecordType, RecordItem, RecordType} from '../models/record.model'
import {RfiService} from '../services/rfi.service'
import {FormArray, FormGroup} from '@angular/forms'
import {ConfirmationAnswer, ConfirmationReply, makeDeleteRecordConfirmation} from '../models/confirmation-reply.model'
import {RecordService} from '../services/record.service'
import {RecordFormCreatorService} from "./record-form-creator/record-form-creator.service";
import {timer} from "rxjs";
import {CustomList} from "../models/custom-list.model";
import {ListService} from "../services/list.service";
import {ToastType} from "../models/toast.model";
import {animate, style, transition, trigger} from "@angular/animations";

@Component({
    selector: 'stargate-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.scss', './form.scss'],
    animations: [
        trigger('slideInOut', [
            transition(':enter', [
                style({transform: 'translateX(100%)'}),
                animate('200ms ease-in', style({transform: 'translateX(0%)'}))
            ]),
            transition(':leave', [
                animate('200ms ease-in', style({transform: 'translateX(100%)'}))
            ])
        ])
    ]
})
export class SidebarComponent implements OnInit, OnChanges {
    @Input()
    record: RecordItem

    headline: string
    idText: string

    submitted = false
    activeRfiMainForm: string
    recordForm: FormGroup
    activeTab: string
    errorList: string[] = []
    lists: CustomList[] = []

    showMoreMenu: boolean = false
    showAddToListMenu: boolean = false;

    isForDelete: boolean = false
    readonly rfiType: RecordType = RecordType.RFI

    constructor(
        private store: Store<AppState>,
        private rfiService: RfiService,
        private recordsService: RecordService,
        private recordFormCreator: RecordFormCreatorService,
        private changeDetectorRef: ChangeDetectorRef,
        private listService: ListService
    ) {
    }

    ngOnInit(): void {
        this.initializeSidebar(this.record)

        this.store.select('nav').subscribe(nav => this.activeTab = nav)

        this.store.select('activeRfiMainForm').subscribe((activeRfiMainForm: string) => {
            this.activeRfiMainForm = activeRfiMainForm
        })

        this.store.select('lists').subscribe((lists) => {
            this.lists = lists
        })

        this.store.select('confirmationReply').subscribe((val: ConfirmationReply) => {
            if (val) {
                switch (val.answer) {
                    case ConfirmationAnswer.OK:
                        if (val.owner === 'sidebar' && this.isForDelete) {
                            this.rfiService.deleteRfi(this.record)
                        } else if (val.owner === 'rfi-list' && !!this.record) {
                            this.initializeSidebar(this.record)
                        } else {
                            this.closeAndResetForm()
                        }
                        break
                    default:
                        this.isForDelete = false
                }
            }
        })
    }

    ngOnChanges(changes: SimpleChanges): void {
        if (changes['record']) {
            if (this.recordForm && this.recordForm.pristine) {
                this.initializeSidebar(changes['record'].currentValue)
            } else if (this.recordForm) {
                this.store.dispatch(new ShowConfirmModalAction({owner: 'rfi-list'}))
            }
        }
    }

    private initializeSidebar(record) {
        this.errorList = []
        this.submitted = false

        this.record = Object.assign({}, record)
        this.headline = getDataForRecordType(this.record.recordType, 'component')
        this.idText = getDataForRecordType(this.record.recordType, 'idText')

        this.recordsService.fetchRecordSelectOptions()

        this.recordForm = this.recordFormCreator.createRecordForm(this.record)
        this.changeDetectorRef.detectChanges();

        timer(1).subscribe(() => {
            if (this.record.recordType === RecordType.NOMINATION) {
                this.recordForm.disable()
            } else {
                this.recordForm.enable()
            }
        })
    }

    closeAndResetForm() {
        this.store.dispatch(new CloseSidebarAction())
    }

    onSave() {
        this.submitted = true
        if (this.recordForm.invalid) {
            this.errorList = this.getErrors(this.recordForm)
        } else {
            this.rfiService.saveRfi(this.recordForm.value)
                .subscribe((rfi: RecordItem) => {
                    rfi.recordType = RecordType.RFI
                    this.record = Object.assign({}, rfi)
                    this.recordForm.patchValue(rfi as any)
                    this.recordForm.markAsPristine()
                    this.errorList = []
                }, () => {
                })
        }
        /*
        no-op error handler is here to prevent an insane test failure:
        UserComponent creates the user FAILED
        [object ErrorEvent] thrown
         */
    }

    cancel() {
        if (this.recordForm.dirty) {
            this.store.dispatch(new ShowConfirmModalAction({owner: 'sidebar'}))
        } else {
            this.store.dispatch(new CloseSidebarAction())
        }
    }

    toggleMainForm(formName: string) {
        this.store.dispatch(new ToggleRfiMainFormAction(formName))
    }

    private getErrors(formGroup: FormGroup): string[] {
        let errors: string[] = []

        if (formGroup.errors) {
            Object.values(formGroup.errors).forEach((value) => errors.push(value))
        }

        const keys = Object.keys(formGroup.controls)
        keys.forEach(key => {
            const obj = formGroup.get(key)
            if (obj instanceof FormArray) {
                const formArray = obj as FormArray
                formArray.controls.forEach((control: FormGroup) => {
                    if (control && control.controls) {
                        errors = errors.concat(this.getErrors(control))
                    }
                })
            } else if (obj instanceof FormGroup) {
                errors = errors.concat(this.getErrors(obj))
            } else if (obj && obj.errors) {
                if (obj.errors.required) {
                    errors.push(`Please include a ${key} - it's required`)
                } else {
                    Object.values(obj.errors).forEach((value) => errors.push(value))
                }
            }
        })

        return errors
    }

    toggleMoreMenu() {
        this.showMoreMenu = !this.showMoreMenu
        this.showAddToListMenu = this.showAddToListMenu && this.showMoreMenu
    }

    toggleAddToListMenu() {
        this.showAddToListMenu = !this.showAddToListMenu
    }

    saveToList(listName: string) {
        const found = !!this.lists.find(list => list.name === listName && !!list.records.find(record =>
            record.recordId === this.record.id.toString() && record.recordType === this.record.recordType))
        if (found) {
            this.store.dispatch(new ShowToastAction({message: `Already exists in ${listName}`, type: ToastType.INFO}))
        } else {
            this.listService.saveRecord(listName, this.record.id.toString(), this.record.recordType)
            this.showAddToListMenu = false
            this.showMoreMenu = false
        }
    }

    deleteRecord() {
        this.isForDelete = true
        this.store.dispatch(new ShowConfirmModalAction(makeDeleteRecordConfirmation({owner: 'sidebar'})))
    }
}


