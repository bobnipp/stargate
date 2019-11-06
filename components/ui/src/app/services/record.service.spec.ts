import {TestBed} from '@angular/core/testing'
import {HttpClient} from '@angular/common/http'
import {MockHttpClient} from '../../test_helpers/mockHttpClient'
import {of} from 'rxjs/internal/observable/of'
import {Store, StoreModule} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {rfiListReducer} from '../state/reducers'
import {ReceivedSelectOptions} from '../state/actions'
import {RecordService} from './record.service'
import {Contracts, validateAndRespond} from '../../test_helpers/contractHelpers'
import {RfiService} from './rfi.service'
import {PrismService} from './prism.service'
import Spy = jasmine.Spy
import {ListService} from "./list.service";
import {RecordType} from "../models/record.model";

describe('RecordService', () => {
    let service: RecordService
    let getSpy: Spy
    let postSpy: Spy
    let deleteSpy: Spy
    let dispatchSpy: Spy
    let store: Store<AppState>

    beforeEach(() => {
        const mockHttpClient = new MockHttpClient()

        TestBed.configureTestingModule({
            providers: [
                RecordService,
                RfiService,
                PrismService,
                ListService,
                {provide: HttpClient, useValue: mockHttpClient},
            ],
            imports: [StoreModule.forRoot({rfis: rfiListReducer})],
        })

        service = TestBed.get(RecordService)
        store = TestBed.get(Store)
        const client = TestBed.get(HttpClient)
        getSpy = spyOn(client, 'get').and.callThrough()
        postSpy = spyOn(client, 'post').and.callThrough()
        deleteSpy = spyOn(client, 'delete').and.callThrough()
        dispatchSpy = spyOn(store, 'dispatch')
    })


    describe('saveRecordLink', () => {
        it('posts to the api to save a record link', () => {
            const recordLink = {record1Id: '2', record1System: 'IMM', record2Id: '123', record2System: 'PRISM'}
            postSpy.and.callFake(validateAndRespond(Contracts.CREATE_RECORD_LINK, of({})))

            service.saveRecordLink(recordLink)
            expect(postSpy).toHaveBeenCalledWith('/api/v1/recordlinks', recordLink)
        })
    })

    describe('deleteRecordLink', () => {
        it('deletes the record link', () => {
            const recordLink = {record1Id: '2', record1System: 'IMM', record2Id: '123', record2System: 'PRISM'}
            deleteSpy.and.returnValue(of({}))

            service.deleteRecordLink(recordLink)
            expect(deleteSpy).toHaveBeenCalledWith('/api/v1/recordlinks?record1Id=2&record2Id=123&record1System=IMM&record2System=PRISM')
        })
    })

    describe('deleteTarget', () => {
        it('deletes the target', () => {
            const target = {
                id: 1,
                rfiId: 1,
                name: 'target name',
                type: 'POINT',
                radius: 1.25,
                radiusUnit: 'KM',
                coordinates: '1.25 1.12'
            }
            deleteSpy.and.returnValue(of({}))

            service.deleteRecordTarget(target)
            expect(deleteSpy).toHaveBeenCalledWith('/api/v1/targets?id=1')
        })
    })

    describe('deleteSensor', () => {
        it('deletes the sensor', () => {
            const sensor = {
                id: 1,
                rfiId: 1,
                sensor: 'sensor name',
                sensorType: 'EO',
                mode: '1',
                desiredQuality: 1,
                requiredQuality: 1
            }
            deleteSpy.and.returnValue(of({}))

            service.deleteRecordSensor(sensor)
            expect(deleteSpy).toHaveBeenCalledWith('/api/v1/sensors?id=1')
        })
    })

    describe('fetchRecordSelectOptions', () => {
        const expectedRequirementTypeOptions = [{id: 1, value: 'Request Type 1'}]
        const expectedSubWorkflowOptions = [{id: 1, value: 'CRM Sub-Workflow 1'}]
        const expectedClassificationOptions = [{id: 1, value: 'Classification 1'}]
        const expectedCaveatOptions = [{id: 1, value: 'Caveat 1'}]
        const expectedSubmittingOrgOptions = [{id: 1, value: 'Submitting Org 1'}]
        const expectedNipfCodeOptions = [{id: 1, value: 'NIPF Code 1'}]
        const expectedPirNameOptions = [{id: 1, value: 'Commander PIR 1'}]
        const expectedCentcomIsrRoleOptions = [{id: 1, value: 'ISR Role 1'}]
        const expectedAssignedTeamOptions = [{id: 1, value: 'Assigned Team 1'}]
        const expectedStatusOptions = [{id: 1, value: 'TO_DO'}]
        const expectedPriorityOptions = [{id: 1, value: 'LOW'}]

        const options = {
            requirementTypeOptions: expectedRequirementTypeOptions,
            subWorkflowOptions: expectedSubWorkflowOptions,
            classificationOptions: expectedClassificationOptions,
            caveatOptions: expectedCaveatOptions,
            submittingOrgOptions: expectedSubmittingOrgOptions,
            nipfCodeOptions: expectedNipfCodeOptions,
            pirNameOptions: expectedPirNameOptions,
            centcomIsrRoleOptions: expectedCentcomIsrRoleOptions,
            assignedTeamOptions: expectedAssignedTeamOptions,
            statusOptions: expectedStatusOptions,
            priorityOptions: expectedPriorityOptions
        }

        it('fetches options for known dropdowns', () => {
            const expectedSelectOptions =  [
                {
                    type: RecordType.RFI,
                    data: options
                },
                {
                    type: RecordType.NOMINATION,
                    data: options
                }
            ]

            getSpy.and.returnValue(of(options))

            service.fetchRecordSelectOptions()

            expect(getSpy).toHaveBeenCalledWith('/api/v1/imm/options')
            expect(getSpy).toHaveBeenCalledWith('/api/v1/prism/options')

            expect(dispatchSpy).toHaveBeenCalledWith(new ReceivedSelectOptions(expectedSelectOptions))
        })
    })
})
