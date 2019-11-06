export interface Attachment {
    name: string
    size: string
    type: string
}

export interface RecordActivity {
    rfiId: number | string
    id?: number | string
    text: string
    username: string
    timestamp: string
    attachments: Attachment[]
}

export interface FileDetails {
    bytes: string
    name: string
    sizeString: string
    size: number
}