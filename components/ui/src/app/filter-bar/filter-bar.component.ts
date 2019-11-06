import {Component, OnInit, QueryList, ViewChildren} from '@angular/core'
import {Store} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {FilterOption, SystemSelectOptions} from '../models/select-option.model'
import {SystemOption} from '../models/system-option.model'
import {RecordService} from '../services/record.service'
import {FilterService} from '../services/filter.service'
import {ClearableFilterComponent} from './filter/clearable-filter.component'
import {getDataForRecordType, RecordType} from "../models/record.model";
import {SelectedFilterOptionState} from "../models/record-filter-state.model";

@Component({
    selector: 'stargate-filter-bar',
    templateUrl: './filter-bar.component.html',
    styleUrls: ['./filter-bar.component.scss']
})
export class FilterBarComponent implements OnInit {
    @ViewChildren('filter') filterComponents: QueryList<ClearableFilterComponent>

    statusOptions: SystemOption[]
    priorityOptions: SystemOption[]
    systemOptions: SystemOption[]
    targetOptions: SystemOption[]
    sensorOptions: SystemOption[]

    selectedSystemOptions: FilterOption[]

    constructor(private store: Store<AppState>, private recordService: RecordService, private filterService: FilterService) {
        this.filterService.storedFilters.subscribe((next: SelectedFilterOptionState) => {
            this.selectedSystemOptions = next.systemList
        })

        this.store.select('recordSelectOptions').subscribe((systemSelectOptions: SystemSelectOptions[]) => {
            this.statusOptions = systemSelectOptions.map(options => {
                return {
                    displayName: getDataForRecordType(options.type, 'filterTitle'),
                    options: options.data.statusOptions.map(option => {
                        return {id: option.id, value: option.value, system: options.type}
                    })
                }
            })

            this.priorityOptions = systemSelectOptions.map(options => {
                return {
                    displayName: getDataForRecordType(options.type, 'filterTitle'),
                    options: options.data.priorityOptions.map(option => {
                        return {id: option.id, value: option.value, system: options.type}
                    })
                }
            })

            this.systemOptions = [{
                displayName: 'COMPONENTS',
                options: [
                    {id: 0, value: getDataForRecordType(RecordType.RFI, 'title'), system: RecordType.RFI},
                    {id: 1, value: getDataForRecordType(RecordType.NOMINATION, 'title'), system: RecordType.NOMINATION},
                ]
            }]

            this.targetOptions = [{
                displayName: 'TARGET',
                options: [
                    {id: 0, value: 'POINT'},
                    {id: 1, value: 'LOC'},
                    {id: 2, value: 'DSA'},
                    {id: 3, value: 'AREA'}
                ]
            }]

            this.sensorOptions = [{
                displayName: 'SENSOR',
                options: [
                    {id: 0, value: 'EO'},
                    {id: 1, value: 'FMV'},
                    {id: 2, value: 'IR'},
                    {id: 3, value: 'SAR'}
                ]
            }]
        })
    }

    ngOnInit(): void {
        this.recordService.fetchRecordSelectOptions()
    }

    hasFilters() {
        return this.filterService.hasFilters()
    }

    clearAllFilters() {
        this.filterComponents.forEach(filterComponent => filterComponent.clearCallback())
        this.filterService.applyFilters(true)
    }

    systemFilterUpdate(systemOptions: FilterOption[]) {
        this.selectedSystemOptions = systemOptions
    }
}
