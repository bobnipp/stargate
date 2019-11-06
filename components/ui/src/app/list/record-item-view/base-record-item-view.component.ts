import {Component, Input, OnInit} from '@angular/core'
import {getDataForRecordType, RecordItem, RecordType} from '../../models/record.model'
import {
    CloseSidebarAction,
    ReceivedOpenLinksAction,
    ShowConfirmModalAction,
    ShowDndRecordDrops,
    ShowToastAction
} from '../../state/actions'
import {RfiService} from '../../services/rfi.service'
import {Store} from '@ngrx/store'
import {AppState} from '../../state/appstate'
import {PrismService} from '../../services/prism.service'
import {CustomList} from "../../models/custom-list.model";
import {ListService} from "../../services/list.service";
import {ToastType} from "../../models/toast.model";
import {
    ConfirmationAnswer,
    ConfirmationReply,
    makeDeleteRecordConfirmation
} from "../../models/confirmation-reply.model";

@Component({
    selector: 'record-item-view',
    templateUrl: './base-record-item-view.component.html',
    styleUrls: ['./base-record-item-view.component.scss']
})
export class BaseRecordItemViewComponent implements OnInit {
    @Input()
    record: RecordItem

    @Input()
    isLinkActive: boolean

    idText: string

    isActive: boolean = false
    openLinks: RecordItem[] = []

    readonly rfiType: RecordType = RecordType.RFI
    readonly nomType: RecordType = RecordType.NOMINATION
    showMoreMenu: boolean
    showAddToListMenu: boolean
    showRevealLinks: boolean = false
    lists: CustomList[]

    isForDelete: boolean = false

    constructor(
        private rfiService: RfiService,
        protected store: Store<AppState>,
        private prismService: PrismService,
        protected listService: ListService
    ) {
        this.store.select('receivedOpenLinks').subscribe(records => {
            if (!!records) {
                this.openLinks = [...records]
            }
        })
        this.store.select('lists').subscribe((lists) => {
            this.lists = lists
        })

        this.store.select('confirmationReply').subscribe((val: ConfirmationReply) => {
            if (val) {
                switch (val.answer) {
                    case ConfirmationAnswer.OK:
                        if (val.owner === 'list-item' && this.isForDelete) {
                            this.rfiService.deleteRfi(this.record)
                        }
                        break
                    default:
                        this.isForDelete = false
                }
            }
        })
    }

    ngOnInit() {
        this.idText = getDataForRecordType(this.record.recordType, 'idText')
        this.showRevealLinks =
            (this.record.coordinates && this.record.coordinates.length > 1) ||
            (this.record.targets && this.record.targets.filter(target => target.coordinates && target.coordinates !== '').length > 0)
    }

    openRecord(record: RecordItem) {
        if (record.recordType === RecordType.RFI) {
            this.rfiService.fetchRfi(record.id)
        } else if (record.recordType === RecordType.NOMINATION) {
            this.prismService.fetchNominationById(record.id)
        }
    }

    doOpenLinkList(record: RecordItem) {
        if (!!record) {
            if (!this.openLinks.find(rec => rec.id === record.id)) {
                this.store.dispatch(new CloseSidebarAction())
                this.store.dispatch(new ReceivedOpenLinksAction([record, ...this.openLinks]))
            }
        }
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
        }
        this.showAddToListMenu = false
        this.showMoreMenu = false
    }

    deleteRecord() {
        this.isForDelete = true
        this.store.dispatch(new ShowConfirmModalAction(makeDeleteRecordConfirmation({owner: 'list-item'})))
    }

    dragStart(dragEvent: any) {
        dragEvent.dataTransfer.setData('text', dragEvent.target.id)
        this.store.dispatch(new ShowDndRecordDrops(true))
    }

    dragEnd(dragEvent: any) {
        this.store.dispatch(new ShowDndRecordDrops(false))
    }
}