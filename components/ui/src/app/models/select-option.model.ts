import {RecordType} from "./record.model";

export interface SelectOption {
    id: number,
    value: string
}

export interface FilterOption extends SelectOption {
    system?: RecordType
    isSelected?: boolean
    isDisabled?: boolean
}

export interface SystemSelectOptions {
    type: RecordType
    data: RecordSelectOptions
}

export interface RecordSelectOptions {
    requirementTypeOptions: SelectOption[]
    subWorkflowOptions: SelectOption[]
    classificationOptions: SelectOption[]
    caveatOptions: SelectOption[]
    submittingOrgOptions: SelectOption[]
    nipfCodeOptions: SelectOption[]
    pirNameOptions: SelectOption[]
    centcomIsrRoleOptions: SelectOption[]
    assignedTeamOptions: SelectOption[]
    statusOptions: SelectOption[]
    priorityOptions: SelectOption[]
}

export function defaultSystemSelectOptions() {
    return [
        {
            type: RecordType.RFI,
            data: {
                requirementTypeOptions: [],
                subWorkflowOptions: [],
                classificationOptions: [],
                caveatOptions: [],
                submittingOrgOptions: [],
                nipfCodeOptions: [],
                pirNameOptions: [],
                centcomIsrRoleOptions: [],
                assignedTeamOptions: [],
                statusOptions: [],
                priorityOptions: []
            }
        },
        {
            type: RecordType.NOMINATION,
            data: {
                requirementTypeOptions: [],
                subWorkflowOptions: [],
                classificationOptions: [],
                caveatOptions: [],
                submittingOrgOptions: [],
                nipfCodeOptions: [],
                pirNameOptions: [],
                centcomIsrRoleOptions: [],
                assignedTeamOptions: [],
                statusOptions: [],
                priorityOptions: []
            }
        }
    ]
}