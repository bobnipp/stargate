import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {RecordItem} from '../../models/record.model'
import * as moment from "moment";
import {RecordHistory} from "../../models/record-history.model";

@Component({
    selector: 'stargate-sidebar-history',
    templateUrl: './sidebar-history.component.html',
    styleUrls: ['./sidebar-history.component.scss']
})
export class SidebarHistoryComponent implements OnInit, OnChanges {
    @Input()
    record: RecordItem;
    recordHistory: RecordHistory[];

    constructor() {
    }

    ngOnInit() {
        this.buildRecordHistory()
    }

    ngOnChanges() {
        this.buildRecordHistory()
    }

    private buildRecordHistory() {
        if (this.record.recordHistory) {
            this.recordHistory = this.record.recordHistory.map(entry => {
                return {
                    username: entry.username,
                    date: this.formatDate(entry.date),
                    action: this.translateAction(entry.action)
                }
            })
        } else {
            this.recordHistory = []
        }
    }

    private formatDate(date: string): string {
        return moment(date).format("MM/DD/YYYY HH:mm:SS")
    }

    private translateAction(action: string): string {
        if (action === 'Insert') {
            return 'Created'
        } else if (action === 'Update') {
            return 'Modified'
        }

        return undefined
    }
}
