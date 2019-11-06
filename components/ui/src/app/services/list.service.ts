import {Injectable} from '@angular/core'
import {HttpClient} from '@angular/common/http'
import {Store} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {ReceivedListAction, ShowToastAction} from '../state/actions'
import {RecordType} from "../models/record.model";
import {ToastType} from "../models/toast.model";
import {CustomList} from "../models/custom-list.model";

@Injectable()
export class ListService {
    basePath = '/api/v1'

    constructor(
        private httpClient: HttpClient,
        private store: Store<AppState>,
    ) {}

    fetchLists() {
        this.httpClient.get(`${this.basePath}/lists`)
            .subscribe((result: any) => this.store.dispatch(new ReceivedListAction(result)))
    }

    saveList(listName: string) {
        this.httpClient.post(`${this.basePath}/lists`, { name: listName })
            .subscribe(() => {this.fetchLists()})
    }

    updateList(list: CustomList) {
        this.httpClient.put(`${this.basePath}/lists`, list).subscribe(() => {})
    }

    saveRecord(listName: string, id: string, type: RecordType) {
        this.httpClient.post(`${this.basePath}/lists/${listName}`, {recordId: id, recordType: type})
            .subscribe(() => {
                this.fetchLists()
                this.store.dispatch(new ShowToastAction({message: `Added to ${listName}`, type: ToastType.SUCCESS}))
            })
    }

    removeRecord(listName: string, id: string, type: RecordType) {
        this.httpClient.delete(`${this.basePath}/lists/${listName}/${id}/${type}`)
            .subscribe(() => {this.fetchLists()})
    }

    deleteList(listName: string) {
        this.httpClient.delete(`${this.basePath}/lists?name=${listName}`)
            .subscribe(() => {
                this.fetchLists()})
    }
}