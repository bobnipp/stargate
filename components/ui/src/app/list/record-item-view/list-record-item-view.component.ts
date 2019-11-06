import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core'
import {BaseRecordItemViewComponent} from './base-record-item-view.component'
import {CustomList, RecordIdentifier} from "../../models/custom-list.model";
import {getDataForRecordType} from "../../models/record.model";

@Component({
    selector: 'list-record-item-view',
    templateUrl: './base-record-item-view.component.html',
    styleUrls: ['./base-record-item-view.component.scss']
})
export class ListRecordItemViewComponent extends BaseRecordItemViewComponent implements OnInit {

    @Input()
    list: CustomList

    @Output()
    removeRecord: EventEmitter<RecordIdentifier> = new EventEmitter<RecordIdentifier>()

    ngOnInit(): void {
        this.idText = getDataForRecordType(this.record.recordType, 'idText')

        this.store.select('activeRecord').subscribe(activeRecord => {
            if (!!activeRecord && activeRecord.id === this.record.id) {
                this.isActive = true
            } else {
                this.isActive = false
            }
        })
    }

    removeFromList() {
        this.removeRecord.emit({recordId: this.record.id.toString(), recordType: this.record.recordType})
    }
}