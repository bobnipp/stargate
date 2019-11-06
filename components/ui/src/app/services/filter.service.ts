import {Injectable} from '@angular/core'
import {AppState} from '../state/appstate'
import {Store} from '@ngrx/store'
import {UpdateFilteredNominationAction, UpdateFilteredRfiAction} from '../state/actions'
import {filterRecords} from '../filter-bar/record-filter'
import {getDefaultRecordFilterState, SelectedFilterOptionState} from '../models/record-filter-state.model'
import {FilterOption} from '../models/select-option.model'
import {RecordItem, RecordType} from '../models/record.model'
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject} from "rxjs/internal/BehaviorSubject";

@Injectable()
export class FilterService {
    basePath = '/api/v1'
    unfilteredNominations: RecordItem[] = []
    unfilteredRfis: RecordItem[] = []
    selectedFilterOptionState: SelectedFilterOptionState

    storedFilters = new BehaviorSubject<SelectedFilterOptionState>(getDefaultRecordFilterState())

    static componentCount: number = 2

    constructor(private store: Store<AppState>, private httpClient: HttpClient) {
        this.storedFilters.subscribe((next: SelectedFilterOptionState) => {
            this.selectedFilterOptionState = next
            this.applyFilters(false)
        })

        this.store.select('prismNominations').subscribe(nominations => {
            this.unfilteredNominations = nominations
            this.applyNominationFilters()
        })

        this.store.select('rfis').subscribe(rfis => {
            this.unfilteredRfis = rfis
            this.applyRfiFilters()
        })

        this.getStoredFiltersForUser()
    }

    getFilterState(filterName: string) {
        return this.selectedFilterOptionState[filterName]
    }

    hasFilters() {
        return !!Object.keys(this.selectedFilterOptionState).find(key => this.selectedFilterOptionState[key] && this.selectedFilterOptionState[key].length > 0)
    }

    updateFilterDontApply(filterName: string, selectedOptions: FilterOption[]) {
        this.selectedFilterOptionState[filterName] = selectedOptions
    }

    updateSearchFilterDontApply(input: string) {
        this.selectedFilterOptionState.keywordSearch = input
    }

    updateFilter(filterName: string, selectedOptions: FilterOption[]) {
        this.selectedFilterOptionState[filterName] = selectedOptions
        this.applyFilters(true)
    }

    updateSearchFilter(input: string) {
        this.selectedFilterOptionState.keywordSearch = input
        this.applyFilters(true)
    }

    applyFilters(doStoreFilters) {
        if (doStoreFilters) {
            this.storeFiltersForUser()
        }
        this.applyRfiFilters()
        this.applyNominationFilters()
    }

    private applyRfiFilters() {
        let filteredRfis = this.applySearchFilter(this.unfilteredRfis)
        filteredRfis = this.applySelectedOptionsFilters(filteredRfis, RecordType.RFI)
        this.store.dispatch(new UpdateFilteredRfiAction(filteredRfis))
    }

    private applyNominationFilters() {
        let filteredNominations = this.applySearchFilter(this.unfilteredNominations)
        filteredNominations = this.applySelectedOptionsFilters(filteredNominations, RecordType.NOMINATION)
        this.store.dispatch(new UpdateFilteredNominationAction(filteredNominations))
    }

    private applySelectedOptionsFilters<T extends RecordItem>(records: T[], systemType: RecordType): T[] {
        if (this.selectedFilterOptionState.systemList.length === 0 || this.selectedFilterOptionState.systemList.length === FilterService.componentCount) {
            records = filterRecords(this.selectedFilterOptionState, records, systemType)
        } else if (this.selectedFilterOptionState.systemList.filter(option => option.system === systemType).length === 1) {
            records = filterRecords(this.selectedFilterOptionState, records, systemType)
        } else if (this.selectedFilterOptionState.systemList.filter(option => option.system === systemType).length === 0) {
            records = []
        }
        return records
    }

    private applySearchFilter<T extends RecordItem>(records: T[]): T[] {
        let searchInput = this.selectedFilterOptionState.keywordSearch
        if (searchInput) {
            searchInput = searchInput.toLowerCase()
            return records.filter(record =>
                (record.id && record.id.toString().toLowerCase().includes(searchInput)) ||
                (record.title && record.title.toLowerCase().includes(searchInput)) ||
                (record.justification && record.justification.toLowerCase().includes(searchInput)) ||
                (record.country && record.country.toLowerCase().includes(searchInput)) ||
                (record.city && record.city.toLowerCase().includes(searchInput)) ||
                (record.region && record.region.toLowerCase().includes(searchInput)) ||
                (record.nai && record.nai.toLowerCase().includes(searchInput)) ||
                (record.prevResearch && record.prevResearch.toLowerCase().includes(searchInput)) ||
                (record.poc && record.poc.toLowerCase().includes(searchInput)) ||
                (record.operation && record.operation.toLowerCase().includes(searchInput)) ||
                (record.targets && record.targets.some(target => target.name.toLowerCase().includes(searchInput)))
            )
        }

        return records
    }

    getStoredFiltersForUser() {
        this.httpClient.get(`${this.basePath}/filters/`).subscribe((result: SelectedFilterOptionState) => {
            if (result){
                this.storedFilters.next(result)
            }
        })
    }

    private storeFiltersForUser() {
        this.httpClient.post(`${this.basePath}/filters/`, this.selectedFilterOptionState)
            .subscribe(() => {})
    }

}