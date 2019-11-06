import {makeRfiListItem, makeRecordTarget, makeRecordSensor} from '../../test_helpers/utilities'
import {filterRecords} from './record-filter'
import {RecordType} from "../models/record.model";

describe('the rfi filtering mechanism', () => {
    describe('filterRfis', () => {
        const target = makeRecordTarget({type: 'POINT'})
        const sensor = makeRecordSensor({sensorType: 'FMV'})
        const rfis = [
            makeRfiListItem({
                id: 1,
                title: 'foo',
                latitude: 1.56,
                longitude: 2.44,
                status: 'TO_DO',
                priority: 'IMMEDIATE',
                targets: [target]
            }),
            makeRfiListItem({
                id: 2,
                title: 'foo',
                latitude: 1.56,
                longitude: 2.44,
                status: 'TO_DO',
                priority: 'IMMEDIATE',
                targets: null,
                sensors: [sensor]
            }),
            makeRfiListItem({id: 3, title: 'bar', status: 'TO_DO', priority: 'IMMEDIATE'}),
            makeRfiListItem({id: 4, title: 'bar', status: 'TO_DO', priority: 'LOW'}),
            makeRfiListItem({id: 5, title: 'bar', status: 'WORKING', priority: 'IMMEDIATE'})
        ]

        it('returns everything when filters are empty', () => {
            const recordFilterState = {
                systemList: [],
                statusList: [],
                priorityList: [],
                targetList: [],
                sensorList: [],
                keywordSearch: ''
            }

            const actual = filterRecords(recordFilterState, rfis, RecordType.RFI)

            expect(actual.map(rfi => rfi.id)).toEqual([1, 2, 3, 4, 5])
        })

        it('filters by status', () => {
            const recordFilterState = {
                systemList: [],
                statusList: [{id: 1, value: 'TO_DO', system: RecordType.RFI}],
                priorityList: [],
                targetList: [],
                sensorList: [],
                keywordSearch: ''
            }

            const actual = filterRecords(recordFilterState, rfis, RecordType.RFI)

            expect(actual.map(rfi => rfi.id)).toEqual([1, 2, 3, 4])
        })

        it('filters by priority', () => {
            const recordFilterState = {
                systemList: [],
                statusList: [],
                priorityList: [{id: 2, value: 'IMMEDIATE', system: RecordType.RFI}],
                targetList: [],
                sensorList: [],
                keywordSearch: ''
            }

            const actual = filterRecords(recordFilterState, rfis, RecordType.RFI)

            expect(actual.map(rfi => rfi.id)).toEqual([1, 2, 3, 5])
        })

        it('filters by target', () => {
            const recordFilterState = {
                systemList: [],
                statusList: [],
                priorityList: [],
                targetList: [{id: 0, value: 'POINT'}],
                sensorList: [],
                keywordSearch: ''
            }

            const actual = filterRecords(recordFilterState, rfis, RecordType.RFI)

            expect(actual.map(rfi => rfi.id)).toEqual([1])
        })

        it('filters by sensor', () => {
            const recordFilterState = {
                systemList: [],
                statusList: [],
                priorityList: [],
                targetList: [],
                sensorList: [{id: 0, value: 'FMV'}],
                keywordSearch: ''
            }

            const actual = filterRecords(recordFilterState, rfis, RecordType.RFI)

            expect(actual.map(rfi => rfi.id)).toEqual([2])
        })

        it('filters by status and priority', () => {
            const recordFilterState = {
                systemList: [],
                statusList: [{id: 1, value: 'TO_DO', system: RecordType.RFI}],
                priorityList: [{id: 2, value: 'IMMEDIATE', system: RecordType.RFI}],
                targetList: [],
                sensorList: [],
                keywordSearch: ''
            }

            const actual = filterRecords(recordFilterState, rfis, RecordType.RFI)

            expect(actual.map(rfi => rfi.id)).toEqual([1, 2, 3])

        })
    })
})