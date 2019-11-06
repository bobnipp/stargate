import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core'
import {RecordItem, trimStringLength} from "../../models/record.model";

@Component({
    selector: 'stargate-links-list',
    templateUrl: './links-list.component.html',
    styleUrls: ['../record-list-view.component.scss', './links-list.component.scss']
})
export class LinksListComponent implements OnInit {

    @Input()
    anchorRecord: RecordItem

    @Input()
    rfis: RecordItem[]

    @Input()
    noms: RecordItem[]

    @Output()
    fireCloseList: EventEmitter<string> = new EventEmitter<string>()

    linkRecords: RecordItem[]

    constructor() {}

    ngOnInit() {
        this.linkRecords = this.anchorRecord.links.map(link => {
            return this.rfis
                .concat(this.noms)
                .find(linkedRecord => {
                    return (linkedRecord.id == link.record2Id || linkedRecord.id == link.record1Id) && linkedRecord.id != this.anchorRecord.id
                })
        })
    }

    getTrimmedString(value: string, maxLength: number) {
        return trimStringLength(value, maxLength)
    }

    closeLinkList() {
        this.fireCloseList.emit(this.anchorRecord.id)
    }
}
