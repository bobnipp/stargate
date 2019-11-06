import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core'
import {getDataForRecordType, RecordItem, RecordType} from "../../models/record.model";
import {RecordService} from "../../services/record.service";
import {AppState} from "../../state/appstate";
import {Store} from "@ngrx/store";
import {SortOption} from "../../models/sort.model";

@Component({
    selector: 'stargate-system-list',
    templateUrl: './system-list.component.html',
    styleUrls: ['../record-list-view.component.scss', './system-list.component.scss']
})
export class SystemListComponent implements OnInit {
    @Input()
    listType: RecordType

    @Input()
    linkRecords: RecordItem[]

    @Output()
    closeList: EventEmitter<RecordType> = new EventEmitter<RecordType>()

    @Output()
    refreshList: EventEmitter<RecordType> = new EventEmitter<RecordType>()

    title: string
    systemList: RecordItem[]
    dataAid: string
    loading: boolean

    isDescendingSort: boolean = true
    sortOptions: SortOption[]
    selectedSort: SortOption
    sortIcon: string = 'arrow_downward'
    showSortOptions: boolean = false

    constructor(private store: Store<AppState>, private recordService: RecordService) {
        this.sortOptions = [{name: 'Date', field: 'createdOn'}, {name: 'Title', field: 'title'}]
        this.selectedSort = this.sortOptions[0]
    }

    ngOnInit() {
        this.store.select(getDataForRecordType(this.listType, 'filteredList')).subscribe((records: RecordItem[]) => {
            this.systemList = records
            this.loading = false
            this.sortList()
        })
        this.title = getDataForRecordType(this.listType, 'title')
        this.dataAid = getDataForRecordType(this.listType, 'dataAid')
        this.loading = true;
        this.recordService.fetchRecords(this.listType)
    }

    toggleList() {
        this.closeList.emit(this.listType)
    }

    isActiveLink(id: string) {
        return !!this.linkRecords.find(l => l.id === id)
    }

    refresh() {
        this.refreshList.emit(this.listType)
    }

    toggleShowSortOptions() {
        this.showSortOptions = !this.showSortOptions
    }

    toggleSortDirection() {
        this.isDescendingSort = !this.isDescendingSort
        this.sortIcon = this.isDescendingSort ? 'arrow_downward' : 'arrow_upward'
        this.showSortOptions = false;
        this.sortList()
    }

    updateSelectedSort(sortName: string) {
        if (this.selectedSort.name !== sortName) {
            this.selectedSort = this.sortOptions.find(option => option.name === sortName)
            this.sortList()
        }

        this.showSortOptions = false;
    }

    private sortList() {
        if (this.selectedSort.name === 'Date') {
            this.sortListByDate()
        } else {
            this.sortListByText()
        }
    }

    private sortListByDate() {
        this.systemList.sort((recordA: RecordItem, recordB: RecordItem) => {
            const recordADate = new Date(recordA[this.selectedSort.field])
            const recordBDate = new Date(recordB[this.selectedSort.field])
            if (this.isDescendingSort) {
                return recordBDate.getTime() - recordADate.getTime()
            } else {
                return recordADate.getTime() - recordBDate.getTime()
            }
        })
    }

    private sortListByText() {
        this.systemList.sort((recordA: RecordItem, recordB: RecordItem) => {
            if (this.isDescendingSort) {
                return recordB[this.selectedSort.field].localeCompare(recordA[this.selectedSort.field])
            } else {
                return recordA[this.selectedSort.field].localeCompare(recordB[this.selectedSort.field])
            }
        })
    }

}
