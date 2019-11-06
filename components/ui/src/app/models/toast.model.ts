export enum ToastType {ERROR='error', INFO='info', SUCCESS='success'}

export interface Toast {
    type: ToastType
    message: string
}