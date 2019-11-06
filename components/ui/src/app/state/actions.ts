import {Action} from '@ngrx/store'
import {RecordItem} from '../models/record.model'
import {SystemSelectOptions} from '../models/select-option.model'
import {User} from '../models/user.model'
import {ConfirmationReply} from '../models/confirmation-reply.model'
import {CustomList} from '../models/custom-list.model'
import {Toast} from "../models/toast.model";

export const RECEIVED_PRISM_NOMINATIONS = 'RECEIVED_PRISM_NOMINATIONS'
export const RECEIVED_RFIS = 'RECEIVED_RFIS'
export const RECEIVED_CONFIG = 'RECEIVED_CONFIG'
export const CHANGED_TAB = 'CHANGED_TAB'
export const OPEN_CREATE_RFI_FORM = 'OPEN_CREATE_RFI_FORM'
export const CLOSE_CREATE_RFI_FORM = 'CLOSE_CREATE_RFI_FORM'
export const UPDATE_FILTERED_RFI_ACTION = 'UPDATE_FILTERED_RFI_ACTION'
export const UPDATE_FILTERED_NOMINATION_ACTION = 'UPDATE_FILTERED_NOMINATION_ACTION'
export const OPEN_EDIT_RFI_FORM = 'OPEN_EDIT_RFI_FORM'
export const OPEN_READ_RECORD_FORM = 'OPEN_READ_RECORD_FORM'
export const CLOSE_EDIT_RFI_FORM = 'CLOSE_EDIT_RFI_FORM'
export const CLOSE_SIDEBAR = 'CLOSE_SIDEBAR'
export const RECEIVED_IMM_RFI_SELECT_OPTIONS = 'RECEIVED_IMM_RFI_SELECT_OPTIONS'
export const TOGGLE_RFI_INFO_SECTION_ACTION = 'TOGGLE_RFI_INFO_SECTION_ACTION'
export const TOGGLE_RFI_MAIN_FORM_ACTION = 'TOGGLE_RFI_MAIN_FORM_ACTION'
export const RECEIVED_CURRENT_USER_ACTION = 'RECEIVED_CURRENT_USER_ACTION'
export const SHOW_CONFIRMATION_MODAL_ACTION = 'SHOW_CONFIRMATION_MODAL_ACTION'
export const CONFIRMATION_REPLY_ACTION = 'CONFIRMATION_REPLY_ACTION'
export const RECEIVED_OPEN_LINKS_ACTION = 'SET_OPEN_LINKS_ACTION'
export const CUSTOM_LIST_CREATED_ACTION = 'CUSTOM_LIST_CREATED_ACTION'
export const RECEIVED_LIST_ACTION = 'RECEIVED_LIST_ACTION'
export const SHOW_TOAST_ACTION = 'SHOW_TOAST_ACTION'
export const SHOW_DND_RECORD_DROPS = 'SHOW_DND_RECORD_DROPS'

export class ReceivedPrismNominationsAction implements Action {
    readonly type = RECEIVED_PRISM_NOMINATIONS

    constructor(public payload: RecordItem[]) {}
}

export class ReceivedRfisAction implements Action {
    readonly type = RECEIVED_RFIS

    constructor(public payload: RecordItem[]) {}
}

export class ReceivedConfigAction implements Action {
    readonly type = RECEIVED_CONFIG

    constructor(public payload: boolean) {}
}

export class ChangeTabAction implements Action {
    readonly type = CHANGED_TAB

    constructor(public payload: string) {}
}

export class OpenCreateRfiFormAction implements Action {
    readonly type = OPEN_CREATE_RFI_FORM

    constructor() {}
}

export class CloseCreateRfiFormAction implements Action {
    readonly type = CLOSE_CREATE_RFI_FORM

    constructor() {}
}

export class OpenEditRfiFormAction implements Action {
    readonly type = OPEN_EDIT_RFI_FORM

    constructor(public payload: RecordItem) {}
}

export class OpenReadOnlyRecordFormAction implements Action {
    readonly type = OPEN_READ_RECORD_FORM

    constructor(public payload: RecordItem) {}
}

export class CloseEditRfiFormAction implements Action {
    readonly type = CLOSE_EDIT_RFI_FORM

    constructor() {}
}

export class CloseSidebarAction implements Action {
    readonly type = CLOSE_SIDEBAR

    constructor() {}
}

export class UpdateFilteredRfiAction implements Action {
    readonly type = UPDATE_FILTERED_RFI_ACTION

    constructor(public payload: RecordItem[]) {}
}

export class UpdateFilteredNominationAction implements Action {
    readonly type = UPDATE_FILTERED_NOMINATION_ACTION

    constructor(public payload: RecordItem[]) {}
}

export class ReceivedSelectOptions implements Action {
    readonly type = RECEIVED_IMM_RFI_SELECT_OPTIONS

    constructor(public payload: SystemSelectOptions[]) {}
}

export class ToggleRfiInfoSectionAction implements Action {
    readonly type = TOGGLE_RFI_INFO_SECTION_ACTION

    constructor(public payload: string) {}
}

export class ToggleRfiMainFormAction implements Action {
    readonly type = TOGGLE_RFI_MAIN_FORM_ACTION

    constructor(public payload: string) {}
}

export class ReceivedCurrentUserAction implements Action {
    readonly type = RECEIVED_CURRENT_USER_ACTION

    constructor(public payload: User) {}
}

export class ShowConfirmModalAction implements Action {
    readonly type = SHOW_CONFIRMATION_MODAL_ACTION

    constructor(public payload: ConfirmationReply) {}
}

export class ConfirmationReplyAction implements Action {
    readonly type = CONFIRMATION_REPLY_ACTION

    constructor(public payload: ConfirmationReply) {}
}

export class CustomListCreatedAction implements Action {
    readonly type = CUSTOM_LIST_CREATED_ACTION

    constructor(public listName: string) {}
}

export class ReceivedListAction implements Action {
    readonly type = RECEIVED_LIST_ACTION

    constructor(public payload: CustomList[]) {}
}

export class ReceivedOpenLinksAction implements Action {
    readonly type = RECEIVED_OPEN_LINKS_ACTION

    constructor(public payload: RecordItem[]) {}
}

export class ShowToastAction implements Action {
    readonly type = SHOW_TOAST_ACTION

    constructor(public payload: Toast) {}
}

export class ShowDndRecordDrops implements Action {
    readonly type = SHOW_DND_RECORD_DROPS

    constructor(public payload: boolean) {}
}

