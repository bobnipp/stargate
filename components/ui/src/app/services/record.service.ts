import {ReceivedSelectOptions} from '../state/actions'
import {HttpClient} from '@angular/common/http'
import {Store} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {Injectable} from '@angular/core'
import {RfiLink} from '../models/rfi-link.model'
import {Target} from '../models/target.model'
import {Attachment, RecordActivity} from '../models/record-activity.model'
import {RfiService} from './rfi.service'
import {PrismService} from './prism.service'
import {RecordType} from "../models/record.model";
import {Sensor} from '../models/sensor.model';
import {forkJoin} from "rxjs";
import {SystemSelectOptions} from "../models/select-option.model";

@Injectable()
export class RecordService {
    basePath = '/api/v1'

    constructor(
        private httpClient: HttpClient,
        private store: Store<AppState>,
        private rfiService: RfiService,
        private prismService: PrismService
    ) {}

    fetchRecords(recordType: RecordType) {
        if (recordType === RecordType.RFI) {
            this.rfiService.fetchRfis()
        } else if (recordType === RecordType.NOMINATION) {
            this.prismService.fetchNominations()
        }
    }

    fetchRecordSelectOptions() {
        forkJoin(
            this.httpClient.get(`${this.basePath}/imm/options`),
            this.httpClient.get(`${this.basePath}/prism/options`)
        ).subscribe(results => {
            const options = [
                { type: RecordType.RFI, data: results[0] },
                { type: RecordType.NOMINATION, data: results[1] }
            ] as SystemSelectOptions[]
            this.store.dispatch(new ReceivedSelectOptions(options))
        })
    }

    saveRecordLink(pendingRecordLink: RfiLink) {
        this.httpClient.post(`${this.basePath}/recordlinks`, pendingRecordLink)
            .subscribe(() => {
                this.rfiService.fetchRfis()
                this.prismService.fetchNominations()
            })
    }

    deleteRecordLink(link: RfiLink) {
        this.httpClient.delete(`${this.basePath}/recordlinks?record1Id=${link.record1Id}&record2Id=${link.record2Id}&record1System=${link.record1System}&record2System=${link.record2System}`)
            .subscribe(() => {
                this.rfiService.fetchRfis()
                this.prismService.fetchNominations()
            })
    }

    deleteRecordTarget(target: Target) {
        this.httpClient.delete(`${this.basePath}/targets?id=${target.id}`)
            .subscribe(() => {})
    }

    deleteRecordSensor(sensor: Sensor) {
        this.httpClient.delete(`${this.basePath}/sensors?id=${sensor.id}`)
            .subscribe(() => {})
    }

    deleteRecordActivity(activity: RecordActivity) {
        this.httpClient.delete(`${this.basePath}/activities?id=${activity.id}`)
            .subscribe(() => {})
    }

    saveRecordActivity(draftRecordActivity: RecordActivity) {
        return this.httpClient.post(`${this.basePath}/activities`, draftRecordActivity, {
            reportProgress: true,
            observe: 'events'
        })
    }

    getAttachment(recordId: number | string, attachment: Attachment) {
        this.httpClient.get(`${this.basePath}/storage/${recordId}/${attachment.name}`).subscribe((fileData: any) => {
            const binaryString = window.atob(fileData.bytes)
            const binaryLen = binaryString.length
            const bytes = new Uint8Array(binaryLen)

            for (let i = 0; i < binaryLen; i++) {
                const ascii = binaryString.charCodeAt(i)
                bytes[i] = ascii
            }

            var blob = new Blob([bytes]);
            const url = window.URL.createObjectURL(blob)
            const a = document.createElement('a')
            document.body.appendChild(a)
            a.setAttribute('style', 'display: none')
            a.href = url
            a.download = attachment.name
            a.click()
            window.URL.revokeObjectURL(url)
            a.remove()
        })
    }
}