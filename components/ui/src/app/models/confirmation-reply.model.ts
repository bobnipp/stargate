export interface ConfirmationReply {
    answer?: ConfirmationAnswer
    owner: string
    okButtonText?: string
    cancelButtonText?: string
    message?: string
    title?: string
}

export enum ConfirmationAnswer { OK = 'ok', CANCEL = 'cancel' }

export const makeDeleteRecordConfirmation = (properties = {}): ConfirmationReply => {
    const defaultProperties = {
        okButtonText: 'YES, DELETE',
        cancelButtonText: 'NO, CANCEL',
        title: 'Delete Record',
        message: 'Are you sure you want to delete this record? You will not be able to recover this information.'
    }
    const mergedProps = Object.assign({}, defaultProperties, properties)
    return mergedProps as ConfirmationReply
}
