import {RecordItem, RecordType} from '../models/record.model'
import {
    CHANGED_TAB,
    ChangeTabAction,
    CLOSE_CREATE_RFI_FORM,
    CLOSE_EDIT_RFI_FORM,
    CLOSE_SIDEBAR,
    CloseCreateRfiFormAction,
    CloseEditRfiFormAction,
    CloseSidebarAction,
    CONFIRMATION_REPLY_ACTION,
    ConfirmationReplyAction,
    CUSTOM_LIST_CREATED_ACTION,
    CustomListCreatedAction,
    OPEN_CREATE_RFI_FORM,
    OPEN_EDIT_RFI_FORM,
    OPEN_READ_RECORD_FORM,
    OpenCreateRfiFormAction,
    OpenEditRfiFormAction,
    OpenReadOnlyRecordFormAction,
    RECEIVED_CONFIG,
    RECEIVED_CURRENT_USER_ACTION,
    RECEIVED_IMM_RFI_SELECT_OPTIONS,
    RECEIVED_LIST_ACTION,
    RECEIVED_OPEN_LINKS_ACTION,
    RECEIVED_PRISM_NOMINATIONS,
    RECEIVED_RFIS,
    ReceivedConfigAction,
    ReceivedCurrentUserAction,
    ReceivedSelectOptions,
    ReceivedListAction,
    ReceivedOpenLinksAction,
    ReceivedPrismNominationsAction,
    ReceivedRfisAction,
    SHOW_CONFIRMATION_MODAL_ACTION,
    ShowConfirmModalAction,
    TOGGLE_RFI_INFO_SECTION_ACTION,
    TOGGLE_RFI_MAIN_FORM_ACTION,
    ToggleRfiInfoSectionAction,
    ToggleRfiMainFormAction,
    UPDATE_FILTERED_NOMINATION_ACTION,
    UPDATE_FILTERED_RFI_ACTION,
    UpdateFilteredNominationAction,
    UpdateFilteredRfiAction,
    SHOW_TOAST_ACTION,
    ShowToastAction, SHOW_DND_RECORD_DROPS, ShowDndRecordDrops
} from './actions'
import {defaultSystemSelectOptions, SystemSelectOptions} from '../models/select-option.model'
import {User} from '../models/user.model'
import {ConfirmationReply} from '../models/confirmation-reply.model'
import {CustomList} from '../models/custom-list.model'
import {Toast} from "../models/toast.model";

const initialCollectionState = []

export function prismNominationsReducer(state: RecordItem[] = initialCollectionState, action: ReceivedPrismNominationsAction) {
    switch (action.type) {
        case RECEIVED_PRISM_NOMINATIONS:
            return action.payload
        default:
            return state
    }
}

export function rfiListReducer(state: RecordItem[] = initialCollectionState, action: ReceivedRfisAction) {
    switch (action.type) {
        case RECEIVED_RFIS:
            return action.payload
        default:
            return state
    }
}

export function filteredRfisReducer(state: RecordItem[] = initialCollectionState, action: UpdateFilteredRfiAction) {
    switch (action.type) {
        case UPDATE_FILTERED_RFI_ACTION:
            return action.payload
        default:
            return state
    }
}

export function filteredNominationsReducer(state: RecordItem[] = initialCollectionState, action: UpdateFilteredNominationAction) {
    switch (action.type) {
        case UPDATE_FILTERED_NOMINATION_ACTION:
            return action.payload
        default:
            return state
    }
}

export function activeRecordReducer(state: RecordItem = null, action: OpenCreateRfiFormAction | CloseCreateRfiFormAction | OpenEditRfiFormAction | CloseEditRfiFormAction | CloseSidebarAction | OpenReadOnlyRecordFormAction) {
    switch (action.type) {
        case OPEN_CREATE_RFI_FORM:
            return {recordType: RecordType.RFI}
        case CLOSE_CREATE_RFI_FORM:
            return null
        case OPEN_EDIT_RFI_FORM:
            return action.payload
        case OPEN_READ_RECORD_FORM:
            return action.payload
        case CLOSE_EDIT_RFI_FORM:
            return null
        case CLOSE_SIDEBAR:
            return null
        default:
            return state
    }
}

export function navigationReducer(state: string = 'MAP', action: ChangeTabAction) {
    switch (action.type) {
        case CHANGED_TAB:
            return action.payload
        default :
            return state
    }
}

export function mapConfigReducer(state: boolean = false, action: ReceivedConfigAction) {
    switch (action.type) {
        case RECEIVED_CONFIG:
            return action.payload
        default :
            return state
    }
}

export function recordSelectOptionsReducer(state: SystemSelectOptions[] = defaultSystemSelectOptions(), action: ReceivedSelectOptions) {
    switch (action.type) {
        case RECEIVED_IMM_RFI_SELECT_OPTIONS:
            return action.payload
        default:
            return state
    }
}

export function activeRfiSubFormReducer(state: string = 'About', action: ToggleRfiInfoSectionAction) {
    switch (action.type) {
        case TOGGLE_RFI_INFO_SECTION_ACTION:
            return action.payload
        default:
            return state
    }
}

export function activeRfiMainFormReducer(state: string = 'Details', action: ToggleRfiMainFormAction) {
    switch (action.type) {
        case TOGGLE_RFI_MAIN_FORM_ACTION:
            return action.payload
        default:
            return state
    }
}

export function currentUserReducer(state: User = null, action: ReceivedCurrentUserAction) {
    switch (action.type) {
        case RECEIVED_CURRENT_USER_ACTION:
            return action.payload
        default:
            return state
    }
}

export function showConfirmationModalReducer(state: ConfirmationReply = undefined, action: ShowConfirmModalAction) {
    switch (action.type) {
        case SHOW_CONFIRMATION_MODAL_ACTION:
            return action.payload
        default:
            return state
    }
}

export function confirmationReplyReducer(state: ConfirmationReply = undefined, action: ConfirmationReplyAction) {
    switch (action.type) {
        case CONFIRMATION_REPLY_ACTION:
            return action.payload
        default:
            return state
    }
}

export function receivedOpenLinksReducer(state: RecordItem[] = [], action: ReceivedOpenLinksAction): RecordItem[] {
    switch (action.type) {
        case RECEIVED_OPEN_LINKS_ACTION:
            return action.payload
        default:
            return state
    }
}

export function customListCreatedReducer(state: string = null, action: CustomListCreatedAction): string {
    switch (action.type) {
        case CUSTOM_LIST_CREATED_ACTION:
            return action.listName
        default:
            return null
    }
}

export function receivedListReducer(state: CustomList[] = [], action: ReceivedListAction): CustomList[] {
    switch (action.type) {
        case RECEIVED_LIST_ACTION:
            return action.payload
        default:
            return state
    }
}

export function showToastReducer(state: Toast = undefined, action: ShowToastAction): Toast {
    switch (action.type) {
        case SHOW_TOAST_ACTION:
            return action.payload
        default:
            return state
    }
}

export function showDndRecordDropsReducer(state: boolean = false, action: ShowDndRecordDrops): boolean {
    switch (action.type) {
        case SHOW_DND_RECORD_DROPS:
            return action.payload
        default:
            return state
    }
}
