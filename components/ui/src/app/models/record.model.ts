import {Target} from './target.model'
import {RfiLink} from './rfi-link.model'
import {RecordActivity} from './record-activity.model'
import {RecordHistory} from './record-history.model'
import {Sensor} from "./sensor.model";

export enum RecordType {RFI = '0', NOMINATION = '1'}

export interface RecordItem {
    id?: any
    title: string
    country: string
    coordinates?: string
    city?: string
    mgrs?: string
    region?: string
    nai?: string
    priority?: string
    status?: string
    justification?: string
    prevResearch?: string
    poc?: string
    customClassification?: string
    operation?: string
    collectionStartDate: string
    collectionEndDate: string
    collectionTerm: string
    collectionType: string
    createdOn: string
    assignee: string
    collectionGuidance: string
    eeis: string[]
    targets: Target[]
    sensors: Sensor[]
    links: RfiLink[]
    activities: RecordActivity[]
    recordHistory: RecordHistory[]
    recordType: RecordType
}

export function getDataForRecordType(recordType: RecordType, fieldName: string) {
    switch (recordType) {
        case RecordType.RFI:
            return rfiRecordTypeData[fieldName]
        case RecordType.NOMINATION:
            return nomRecordTypeData[fieldName]
        default:
    }
}

export function trimStringLength(val: string, maxLength: number) {
    if (val.length > maxLength) {
        return `${val.substr(0, maxLength)}...`
    }

    return val
}

const rfiRecordTypeData = {
    component: 'IMM',
    idText: 'RFI',
    title: 'IMM RFIs',
    filterLabel: 'IMM RFIs',
    filterTitle: 'RFI',
    filterKey: 'rfi',
    dataAid: 'rfi-list',
    filteredList: 'filteredRfis'
}

const nomRecordTypeData = {
    component: 'PRISM',
    idText: 'NOM',
    title: 'PRISM Nominations',
    filterLabel: 'PRISM NOMs',
    filterTitle: 'Nomination',
    filterKey: 'nom',
    dataAid: 'requirements-list',
    filteredList: 'filteredNominations'
}
