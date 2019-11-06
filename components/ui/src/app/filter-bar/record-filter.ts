import {SelectedFilterOptionState} from '../models/record-filter-state.model'
import {FilterOption} from '../models/select-option.model'
import {RecordItem, RecordType} from '../models/record.model'

function filterBy(filterOptions: FilterOption[], record: RecordItem, system: RecordType, field: string) {
    const immFilterList = filterOptions

    return immFilterList.length === 0
        || !!immFilterList.find((option: FilterOption) => {
            let targetTypeMatch
            let sensorTypeMatch
            if (record.targets) {
                targetTypeMatch = record.targets.find((target) => {
                    return target[field] === option.value
                })
            }
            if (record.sensors) {
                sensorTypeMatch = record.sensors.find((sensor) => {
                    return sensor[field] === option.value
                })
            }
            return (option.system === system || system === null) && (option.value === record[field] || !!sensorTypeMatch || !!targetTypeMatch)
        })
}

export function filterRecords<T extends RecordItem>(recordFilterState: SelectedFilterOptionState, records: T[], system: RecordType): T[] {
    return records.filter(record => {
        return filterBy(recordFilterState.statusList, record, system, 'status')
            && filterBy(recordFilterState.priorityList, record, system, 'priority')
            && filterBy(recordFilterState.targetList, record, null, 'type')
            && filterBy(recordFilterState.sensorList, record, null, 'sensorType')
    })

}
