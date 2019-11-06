import {Injectable} from '@angular/core'
import {HttpClient} from '@angular/common/http'
import {Store} from '@ngrx/store'
import {CloseSidebarAction, OpenEditRfiFormAction, ReceivedRfisAction, ShowToastAction,} from '../state/actions'
import {AppState} from '../state/appstate'
import {share} from 'rxjs/operators'
import {RecordItem, RecordType} from '../models/record.model'
import {ListService} from "./list.service";
import {PrismService} from "./prism.service";
import {ToastType} from "../models/toast.model";

@Injectable()
export class RfiService {
    basePath = '/api/v1/imm'

    constructor(private httpClient: HttpClient, private store: Store<AppState>, private prismService: PrismService, private listService: ListService) {}

    saveRfi(rfi: RecordItem) {
        if (!!rfi.id) {
            return this.updateRfi(rfi)
        } else {
            return this.createRfi(rfi)
        }
    }

    fetchRfi(rfiId) {
        this.httpClient.get(`${this.basePath}/rfis/${rfiId}`).subscribe((rfi: RecordItem) => {
            this.store.dispatch(new OpenEditRfiFormAction(Object.assign({}, rfi, {recordType: RecordType.RFI})))
        })
    }

    fetchRfis() {
        this.httpClient.get(`${this.basePath}/rfis`).subscribe((rfis: RecordItem[]) => {
            this.store.dispatch(new ReceivedRfisAction(
                rfis.map( (rfi: RecordItem) => {
                    rfi.recordType = RecordType.RFI
                    return rfi
                })))
        })
    }

    deleteRfi(rfi) {
        this.httpClient.delete(`${this.basePath}/rfis/${rfi.id}`).subscribe(() => {
            this.fetchRfis()
            this.prismService.fetchNominations()
            this.listService.fetchLists()
            this.store.dispatch(new CloseSidebarAction())
            this.store.dispatch(new ShowToastAction({message: `${rfi.title} has been deleted`, type: ToastType.SUCCESS}))
        })
    }

    private updateRfi(rfi: RecordItem) {
        const updateRfiObservable = this.httpClient.put(`${this.basePath}/rfis/${rfi.id}`, rfi).pipe(share())
        updateRfiObservable.subscribe(() => this.fetchRfis())
        return updateRfiObservable
    }

    private createRfi(rfi: RecordItem) {
        const createRfiObservable = this.httpClient.post(`${this.basePath}/rfis`, rfi).pipe(share())
        createRfiObservable.subscribe(() => this.fetchRfis())
        return createRfiObservable
    }
}
