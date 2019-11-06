import {RecordType} from "./record.model";

export interface RecordIdentifier {
    recordId: string,
    recordType: RecordType
}

export interface CustomList {
    name: string
    records: RecordIdentifier[]
}