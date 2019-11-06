import {FilterOption} from './select-option.model'

export interface SelectedFilterOptionState {
    systemList: FilterOption[]
    statusList: FilterOption[]
    priorityList: FilterOption[]
    targetList: FilterOption[]
    sensorList: FilterOption[]
    keywordSearch: string
}

export function getDefaultRecordFilterState(): SelectedFilterOptionState {
    return {
        systemList: [],
        statusList: [],
        priorityList: [],
        targetList: [],
        sensorList: [],
        keywordSearch: ''
    }
}
