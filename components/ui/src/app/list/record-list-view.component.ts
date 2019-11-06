import {AfterViewInit, Component, OnInit, QueryList, ViewChildren} from '@angular/core'
import {RecordItem, RecordType, trimStringLength} from '../models/record.model'
import {Store} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {ReceivedOpenLinksAction, ShowToastAction} from '../state/actions'
import {ListService} from '../services/list.service'
import {CustomList} from '../models/custom-list.model'
import {ToastType} from "../models/toast.model";
import {RecordService} from "../services/record.service";

@Component({
    selector: 'stargate-rfi',
    templateUrl: './record-list-view.component.html',
    styleUrls: ['./record-list-view.component.scss']
})
export class RecordListViewComponent implements OnInit, AfterViewInit {

    @ViewChildren('linksList')
    linksList: QueryList<any>

    @ViewChildren('customList')
    customList: QueryList<any>

    rfis: RecordItem[] = []
    nominations: RecordItem[] = []

    activeRecord: RecordItem

    listsBarOpen: boolean = true
    showRFIList: boolean = true
    showNOMList: boolean = true
    showAddList: boolean = false

    linkRecords: RecordItem[] = []
    lists: CustomList[] = []
    showLists: CustomList[] = []

    readonly rfiRecordType: RecordType = RecordType.RFI
    readonly nomRecordType: RecordType = RecordType.NOMINATION

    isAddingList: boolean = false

    linksListAdded: boolean = false
    customListAdded: boolean = false


    constructor(private store: Store<AppState>, private listService: ListService,
                private recordService: RecordService) {
        this.store.select('activeRecord').subscribe(rfi => this.activeRecord = rfi)

        this.store.select('rfis').subscribe(rfis => {
            this.rfis = rfis
            this.updateLinkRecords()
        })

        this.store.select('prismNominations').subscribe(noms => {
            this.nominations = noms
            this.updateLinkRecords()
        })

        this.store.select('receivedOpenLinks').subscribe(records => {
            if (!!records) {
                if (this.linkRecords.length < records.length) {
                    this.linksListAdded = true
                }
                this.linkRecords = Object.assign([], records)
            }
        })

        this.store.select('lists').subscribe(lists => {
            let addedList: CustomList
            if (this.isAddingList) {
                if (this.lists && this.lists.length > 0) {
                    addedList = lists.find(incomingList => !this.lists.find(list => list.name === incomingList.name))
                } else if (lists && lists.length === 1) {
                    addedList = lists[0]
                }

                this.isAddingList = false
            }

            this.lists = lists.sort((a: CustomList, b: CustomList) => {
                if (a.name.toLowerCase() < b.name.toLowerCase()) return -1
                if (a.name.toLowerCase() > b.name.toLowerCase()) return 1
                return 0
            })

            this.showLists = this.showLists.map(showList => {
                return this.lists.find(list => list.name === showList.name)
            })

            if (addedList) {
                this.showLists.push(addedList)
                this.customListAdded = true
            }
        })
    }

    ngOnInit() {
        this.listService.fetchLists()
    }

    ngAfterViewInit() {
        this.linksList.changes.subscribe((changes) => {
            if (this.linksListAdded) {
                this.scrollToList('links-list')
                this.linksListAdded = false
            }
        })

        this.customList.changes.subscribe((changes) => {
            if (this.customListAdded) {
                this.scrollToList('custom-list')
                this.customListAdded = false
            }
        })
    }

    closeLinkList(recordId: string) {
        this.store.dispatch(new ReceivedOpenLinksAction(
            this.linkRecords.filter(link => link.id !== recordId)
        ))
    }

    closeCustomList(listName) {
        this.showLists = this.showLists.filter(list => list.name !== listName)
    }

    toggleListBar() {
        this.listsBarOpen = !this.listsBarOpen
    }

    toggleCustomList(listName) {
        if (!!this.showLists.find(list => list.name === listName)) {
            this.showLists = this.showLists.filter(list => list.name !== listName)
        } else {
            this.showLists = [...this.showLists, this.lists.find(list => list.name === listName)]
            this.customListAdded = true
        }
    }

    isCustomListActive(listName) {
        return !!this.showLists.find(list => list.name === listName)
    }

    toggleList(recordType: RecordType) {
        if (recordType === RecordType.RFI) {
            this.showRFIList = !this.showRFIList
        } else if (recordType === RecordType.NOMINATION) {
            this.showNOMList = !this.showNOMList
        }
    }

    refreshList(recordType: RecordType) {
        this.recordService.fetchRecords(recordType)
    }

    getTrimmedString(value: string, maxLength: number) {
        return trimStringLength(value, maxLength)
    }

    handleAddListClose(listName: string) {
        this.showAddList = false
        const foundList = this.lists.find(list => list.name === listName)

        if (foundList) {
            this.store.dispatch(new ShowToastAction({
                type: ToastType.INFO,
                message: `List with name '${listName}' already exists`
            }))
            this.toggleCustomList(foundList.name)
        } else if (!foundList && listName && listName.length > 0) {
            this.isAddingList = true
            this.listService.saveList(listName)
        }
    }

    deleteList(listName: string) {
        this.listService.deleteList(listName)
    }

    private updateLinkRecords() {
        const recordList = this.rfis.concat(this.nominations)
        this.linkRecords = recordList.filter(record => !!this.linkRecords.find(linkRecord => linkRecord.id === record.id && linkRecord.recordType === record.recordType))
    }

    private scrollToList(listClassName: string) {
        const elements = document.getElementsByClassName(listClassName)
        if (elements && elements.length > 0) {
            elements[elements.length - 1].scrollIntoView({behavior: 'smooth'})
        }
    }
}
