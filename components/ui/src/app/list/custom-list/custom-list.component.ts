import {Component, ElementRef, EventEmitter, Input, NgZone, OnInit, Output, ViewChild} from '@angular/core'
import {CustomList, RecordIdentifier} from "../../models/custom-list.model";
import {RecordItem, RecordType, trimStringLength} from "../../models/record.model";
import {ListService} from "../../services/list.service";
import {Store} from "@ngrx/store";
import {AppState} from "../../state/appstate";
import {ShowToastAction} from "../../state/actions";
import {ToastType} from "../../models/toast.model";

@Component({
    selector: 'stargate-custom-list',
    templateUrl: './custom-list.component.html',
    styleUrls: ['../record-list-view.component.scss', './custom-list.component.scss']
})
export class CustomListComponent implements OnInit {
    @Input()
    list: CustomList

    @Input()
    rfis: RecordItem[]

    @Input()
    noms: RecordItem[]

    @Output()
    fireCloseList: EventEmitter<string> = new EventEmitter<string>()

    @Output()
    fireDeleteList: EventEmitter<string> = new EventEmitter<string>()

    @ViewChild('dropDiv')
    dropDiv: ElementRef

    listRecords: RecordItem[]
    showMenu: boolean = false
    showRecordDrops: boolean = false

    constructor(private listService: ListService, private store: Store<AppState>, private ngZone: NgZone) {
        this.store.select('showDndRecordDrops').subscribe(showRecordDrops => this.showRecordDrops = showRecordDrops)
    }

    ngOnInit() {
        this.setRecordList()

        this.ngZone.runOutsideAngular(() => {
            this.dropDiv.nativeElement.addEventListener('dragover', (dragEvent: DragEvent) => {
                dragEvent.preventDefault()
            })
        })
    }

    closeList() {
        this.fireCloseList.emit(this.list.name)
    }

    deleteList() {
        this.fireDeleteList.emit(this.list.name)
    }

    toggleMenu() {
        this.showMenu = !this.showMenu
    }

    closeMenu() {
        this.showMenu = false;
    }

    drop(dragEvent: any) {
        dragEvent.preventDefault()
        const droppedOnElement: Element = this.getCurrentElement(dragEvent)
        const eventText = dragEvent.dataTransfer.getData('text')
        if (droppedOnElement) {
            this.reorgList(eventText, droppedOnElement.id)
            this.setRecordList()
        } else if (this.listRecords.length === 0) {
            this.list.records = [{recordId: this.getRecordId(eventText), recordType: this.getRecordType(eventText)}]
            this.store.dispatch(new ShowToastAction({message: `Added to ${this.list.name}`, type: ToastType.SUCCESS}))
            this.listService.updateList(this.list)
            this.setRecordList()
        }
    }

    getCurrentElement(dragEvent): Element {
        return dragEvent.target.closest('.list-table_item')
    }

    reorgList(dropperId, droppeeId) {
        if (dropperId === droppeeId) {
            return
        }

        const dropperRecordId = this.getRecordId(dropperId)
        const dropperRecordType = this.getRecordType(dropperId)
        const droppeeRecordId = this.getRecordId(droppeeId)
        const droppeeRecordType = this.getRecordType(droppeeId)

        const dropperRecordIndex = this.list.records.findIndex(record => record.recordId === dropperRecordId && record.recordType === dropperRecordType)
        const droppeeRecordIndex = this.list.records.findIndex(record => record.recordId === droppeeRecordId && record.recordType === droppeeRecordType)
        this.list.records.splice(droppeeRecordIndex, 0, {recordId: dropperRecordId, recordType: dropperRecordType})

        if (dropperRecordIndex !== -1) {
            this.list.records.splice((droppeeRecordIndex > dropperRecordIndex) ? dropperRecordIndex : dropperRecordIndex + 1, 1)
        } else {
            this.store.dispatch(new ShowToastAction({message: `Added to ${this.list.name}`, type: ToastType.SUCCESS}))
        }

        this.listService.updateList(this.list)
    }

    removeRecord(identifier: RecordIdentifier) {
        this.list.records = this.list.records.filter(record => record.recordId !== identifier.recordId || record.recordType !== identifier.recordType)
        this.listService.updateList(this.list)
        this.setRecordList()
    }

    getTrimmedString(value: string, maxLength: number) {
        return trimStringLength(value, maxLength)
    }

    private getRecordId(idRecordType): string {
        let idArray = idRecordType.split(':')
        idArray.pop()
        return idArray.join(':')
    }

    private getRecordType(idRecordType): RecordType {
        return idRecordType.split(':').pop()
    }

    private setRecordList() {
        this.listRecords = this.list.records.map(item => {
            return this.rfis
                .concat(this.noms)
                .find(record => {
                    return record.id == item.recordId
                })
        })
    }

}
