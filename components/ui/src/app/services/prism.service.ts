import {Injectable} from '@angular/core'
import {Store} from '@ngrx/store'
import {HttpClient} from '@angular/common/http'
import {AppState} from '../state/appstate'
import {OpenReadOnlyRecordFormAction, ReceivedPrismNominationsAction} from '../state/actions'
import {RecordItem, RecordType} from '../models/record.model'
import * as moment from 'moment'

@Injectable()
export class PrismService {
    basePath = '/api/v1/prism'

    constructor(private httpClient: HttpClient, private store: Store<AppState>) {}

    fetchNominations() {
        this.httpClient.get(`${this.basePath}/nominations`)
            .subscribe((response: RecordItem[]) => {
                this.store.dispatch(new ReceivedPrismNominationsAction(
                    response.map( (nom: RecordItem) => {
                        nom.recordType = RecordType.NOMINATION
                        return nom
                    })))
            })
    }

    fetchNominationById(nomId) {
        this.httpClient.get(`${this.basePath}/nominations/${nomId.replace('#', '%23')}`)
            .subscribe((response: RecordItem) => {
                const defaults: RecordItem = {
                    id: '',
                    title: '',
                    country: '',
                    coordinates: '',
                    city: '',
                    mgrs: '',
                    region: '',
                    nai: '',
                    priority: '',
                    status: '',
                    justification: '',
                    prevResearch: '',
                    poc: '',
                    customClassification: '',
                    operation: '',
                    collectionStartDate: '',
                    collectionEndDate: '',
                    collectionTerm: '',
                    collectionType: '',
                    createdOn: '',
                    assignee: '',
                    collectionGuidance: '',
                    eeis: [],
                    targets: [],
                    sensors: [],
                    links: [],
                    activities: [],
                    recordHistory: [],
                    recordType: RecordType.NOMINATION
                }
                Object.keys(response).forEach((key) => {
                    if (response[key] === null) {
                        response[key] = defaults[key]
                    }
                })
                response.collectionStartDate = moment.utc(response.collectionStartDate, 'YYYY-MM-DD').format('DD MMM YYYY HH:mm')
                response.collectionEndDate = moment.utc(response.collectionEndDate, 'YYYY-MM-DD').format('DD MMM YYYY HH:mm')
                response.recordType = defaults.recordType
                this.store.dispatch(new OpenReadOnlyRecordFormAction(response))
            })
    }
}