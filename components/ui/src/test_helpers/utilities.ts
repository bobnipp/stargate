import {User} from '../app/models/user.model'
import {MarkerModel} from '../app/models/marker.model'
import {RequirementMarkerModel} from '../app/models/requirement-marker.model'
import {Target} from '../app/models/target.model'
import {RecordItem, RecordType} from '../app/models/record.model'
import {Sensor} from "../app/models/sensor.model";

export const defaultRfiListProperties = {
    id: 1,
    title: '',
    status: 'TO_DO',
    priority: 'LOW',
    justification: '',
    coordinates: '',
    createdOn: '',
    targets: [],
    sensors: [],
    city: '',
    country: '',
    nai: '',
    operation: '',
    poc: '',
    prevResearch: '',
    region: '',
    recordType: RecordType.RFI
}

export const defaultPrismNominationProperties = {
    id: 'id1',
    status: 'status',
    priority: 'priority',
    title: 'title',
    createdOn: '',
    targets: [],
    sensors: [],
    city: '',
    country: '',
    nai: '',
    operation: '',
    poc: '',
    prevResearch: '',
    region: '',
    recordType: RecordType.NOMINATION
}

export const makeRfiListItem = (properties = {}): RecordItem => {
    const mergedProps = Object.assign({}, defaultRfiListProperties, properties)
    return mergedProps as RecordItem
}

export const makeNomCrListItem = (properties = {}): RecordItem => {
    const defaultProperties = {
        id: '1',
        title: '',
        status: 'TO_DO',
        priority: 'Immediate',
        createdOn: '2018-1-1',
        targets: [],
        city: '',
        country: '',
        nai: '',
        operation: '',
        poc: '',
        prevResearch: '',
        region: '',
        recordType: RecordType.NOMINATION
    }

    return Object.assign({}, defaultProperties, properties) as RecordItem
}

export const makePrismNomination = (properties = {}): RecordItem => {
    const defaultProperties = {
        id: '1',
        title: '',
        country: '',
        coordinates: '',
        city: '',
        mgrs: '',
        region: '',
        nai: '',
        priority: '',
        status: '',
        justification: '',
        prevResearch: '',
        poc: '',
        customClassification: '',
        operation: '',
        collectionStartDate: '',
        collectionEndDate: '',
        collectionTerm: '',
        collectionType: '',
        createdOn: '',
        assignee: '',
        collectionGuidance: '',
        eeis: ['', '', ''],
        targets: [],
        sensors: [],
        links: [],
        activities: [],
        recordHistory: [],
        recordType: RecordType.NOMINATION
    }

    return Object.assign({}, defaultProperties, properties) as RecordItem
}

export const makeRfi = (properties = {}): RecordItem => {
    const defaultProperties = {
        id: 1,
        title: '',
        country: '',
        coordinates: '',
        city: '',
        region: '',
        nai: '',
        priority: 'LOW',
        status: 'TO_DO',
        justification: '',
        prevResearch: '',
        requirementTypeId: 1,
        subWorkflowId: 1,
        classificationId: 1,
        caveatId: 1,
        submittingOrgId: 1,
        nipfCodeId: 1,
        pirNameId: 1,
        centcomIsrRoleId: 1,
        operation: '',
        customClassification: '',
        poc: '',
        collectionStartDate: '',
        collectionEndDate: '',
        collectionTerm: '',
        collectionType: '',
        createdOn: '',
        assignedTeamId: 1,
        assignee: '',
        collectionGuidance: '',
        eeis: ['', '', ''],
        links: [],
        activities: [],
        targets: [],
        sensors: [],
        recordHistory: [],
        recordType: RecordType.RFI
    }

    return Object.assign({}, defaultProperties, properties) as RecordItem
}

export const makeNewRfi = (properties = {}): RecordItem => {
    const defaultProperties = {
        title: '',
        country: '',
        coordinates: '',
        city: '',
        region: '',
        nai: '',
        priority: 'LOW',
        status: 'TO_DO',
        justification: '',
        prevResearch: '',
        requirementTypeId: 1,
        subWorkflowId: 1,
        classificationId: 1,
        caveatId: 1,
        submittingOrgId: 1,
        nipfCodeId: 1,
        pirNameId: 1,
        centcomIsrRoleId: 1,
        operation: '',
        customClassification: '',
        poc: '',
        collectionStartDate: '',
        collectionEndDate: '',
        collectionTerm: '',
        collectionType: '',
        createdOn: '',
        assignedTeamId: 1,
        assignee: '',
        collectionGuidance: '',
        eeis: ['', '', ''],
        links: [],
        activities: [],
        targets: [],
        sensors: [],
        recordHistory: [],
        recordType: RecordType.RFI
    }

    return Object.assign({}, defaultProperties, properties) as RecordItem
}

export const makeUser = (properties = {}): User => {
    const defaultProperties = {
        id: undefined,
        username: '',
        firstName: '',
        lastName: '',
        email: '',
        notes: '',
        showUserDetails: false
    }
    return Object.assign({}, defaultProperties, properties)
}

export const makeIssueMapMarker = (properties = {}): MarkerModel => {
    const defaultProperties = {
        title: '',
        latitude: 0,
        longitude: 0,
        iconUrl: '',
        id: 1,
        markerType: '',
        isOpen: false,
        isActive: false,
        status: 'TO_DO'
    }

    return Object.assign({}, defaultProperties, properties)
}

export const makeRequirementMapMarker = (properties = {}): RequirementMarkerModel => {
    const defaultProperties = {
        id: '',
        title: '',
        description: '',
        iconUrl: '',
        latitude: 0,
        longitude: 0,
        markerType: '',
        isOpen: false,
        isActive: false
    }

    return Object.assign({}, defaultProperties, properties)
}

export const makeRecordTarget = (properties = {}): Target => {
    const defaultProperties = {
        rfiId: null,
        name: '',
        type: '',
        radius: null,
        radiusUnit: '',
        coordinates: ''
    }
    return Object.assign({}, defaultProperties, properties)
}

export const makeRecordSensor = (properties = {}): Sensor => {
    const defaultProperties = {
        id: 0,
        rfiId: null,
        sensor: '',
        sensorType: '',
        mode: null,
        requiredQuality: 1,
        desiredQuality: 2
    }
    return Object.assign({}, defaultProperties, properties)
}
