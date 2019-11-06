import {of} from 'rxjs/internal/observable/of'

declare const require: any
const tv4 = require('tv4')

export enum Contracts {
    GET_PRISM_NOMINATIONS = 'getPrismNominations',
    GET_USER = 'getUser',
    GET_RFI = 'getRfi',
    CREATE_RFI = 'createRfi',
    UPDATE_RFI = 'updateRfi',
    LIST_RFIS = 'listRfis',
    GET_PRIORITIES = 'getPriorities',
    GET_REQUIREMENTS = 'getRequirements',
    LIST_RFI_OPTIONS = 'listRfiOptions',
    CREATE_RECORD_LINK = 'createRecordLink',
    LIST_RFI_LINKS = 'getRfiLinks',
    GET_ACTIVITIES = 'getActivities',
    CREATE_ACTIVITY = 'createActivity'
}

export function validateAndRespond(contract, response) {
    const config = contractMap[contract]
    if (config === undefined) throw new Error(`No contracts defined in contractMap for ${contract}!!`)

    return (requestUrl, requestBody) => {
        validateRequest(requestBody, contract)
        validateResponse(response, contract)
        return of(response)
    }
}

export function validateRequest(object, contract) {
    const path = contractMap[contract].request
    if (path === undefined) throw new Error(`No request contract defined in contractMap for key ${contract}`)
    if (path !== null) validateObject(object, path)
    return object
}

export function validateResponse(object, contract) {
    const path = contractMap[contract].response
    if (path === undefined) throw new Error(`No response contract defined in contractMap for key ${contract}`)
    if (path !== null) validateObject(object, path)
    return object
}

function validateObject(object, path) {
    const contract = getSchema(path)
    const validation = tv4.validateMultiple(object, contract)
    if (!validation.valid) {
        throw new Error(validation.errors)
    }
}

function getSchema(filename: String) {
    return require(`../../../api/src/test/resources/contracts/${filename}.contract.json`)
}

const contractMap = {
    getRfi: {request: null, response: 'rfi/rfi'},
    createRfi: {request: 'rfi/create-rfi.request', response: 'rfi/rfi'},
    updateRfi: {request: 'rfi/rfi', response: null},
    listRfis: {request: null, response: 'rfi/rfi-list'},
    getPriorities: {request: null, response: 'metadata/get.priorities'},
    getRequirements: {request: null, response: 'prism/get.requirements'},
    listRfiOptions: {request: null, response: 'rfi/rfi-option-list'},
    createRecordLink: {request: 'rfi/create-rfi-link.request', response: null},
    getRfiLinks: {request: null, response: 'rfi/rfi-links'},
    getUser: {request: null, response: 'user/user'},
    getActivities: {request: null, response: 'rfi/rfi-activities'},
    createActivity: {request: 'rfi/create-rfi-activity.request', response: 'rfi/rfi-activity'},
    getPrismNominations: {request: null, response: 'prism/get.noms'}
}
