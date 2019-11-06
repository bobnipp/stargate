import {SelectOption} from '../models/select-option.model'
import {User} from '../models/user.model'
import {RecordItem} from "../models/record.model";

export interface AppState {
    rfis: RecordItem[]
    receivedConfig: boolean
    nav: string
    activeRecord: RecordItem
    requirementTypeOptions: SelectOption[]
    subWorkflowOptions: SelectOption[]
    classificationOptions: SelectOption[]
    caveatOptions: SelectOption[]
    submittingOrgOptions: SelectOption[]
    nipfCodeOptions: SelectOption[]
    pirNameOptions: SelectOption[]
    centcomIsrRoleOptions: SelectOption[]
    currentUser: User
    prismNominations: RecordItem[]
    filteredNominations: RecordItem[]
    filteredRfis: RecordItem[]
}