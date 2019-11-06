import {TestBed} from '@angular/core/testing'
import {Store, StoreModule} from '@ngrx/store'
import {prismNominationsReducer, rfiListReducer} from '../state/reducers'
import {FilterService} from './filter.service'
import {AppState} from '../state/appstate'
import {makeNomCrListItem, makeRfiListItem, makeRecordTarget} from '../../test_helpers/utilities'
import {
    ReceivedPrismNominationsAction,
    ReceivedRfisAction,
    UpdateFilteredNominationAction,
    UpdateFilteredRfiAction
} from '../state/actions'
import Spy = jasmine.Spy
import {RecordType} from "../models/record.model";
import {MockHttpClient} from "../../test_helpers/mockHttpClient";
import {HttpClient} from "@angular/common/http";
import {of} from '../../../node_modules/rxjs';

describe('FilterService', () => {
    let subject: FilterService
    let store: Store<AppState>
    let dispatchSpy: Spy
    let getSpy: Spy
    let postSpy: Spy

    beforeEach(() => {
        const mockHttpClient = new MockHttpClient()

        TestBed.configureTestingModule({
            providers: [
                FilterService,
                {provide: HttpClient, useValue: mockHttpClient},
            ],
            imports: [
                StoreModule.forRoot({
                    rfis: rfiListReducer,
                    prismNominations: prismNominationsReducer
                })],
        })

        const client = TestBed.get(HttpClient)
        getSpy = spyOn(client, 'get').and.callThrough()
        postSpy = spyOn(client, 'post').and.returnValue(of({}))

        store = TestBed.get(Store)
        subject = TestBed.get(FilterService)
        dispatchSpy = spyOn(store, 'dispatch').and.callThrough()
    })

    describe('hasFilters', () => {
        it('returns true if the user has selected any filters', () => {
            subject.selectedFilterOptionState = {
                systemList: [],
                statusList: [],
                priorityList: [{id: 1, value: 'RFI_Working', system: RecordType.RFI}],
                targetList: [],
                sensorList: [],
                keywordSearch: ''
            }
            expect(subject.hasFilters()).toBeTruthy()

            subject.selectedFilterOptionState = {
                systemList: [],
                statusList: [{id: 1, value: 'FANTASTIC!!!', system: RecordType.NOMINATION}],
                priorityList: [{id: 2, value: 'RFI_Working', system: RecordType.RFI}],
                targetList: [],
                sensorList: [],
                keywordSearch: ''
            }
            expect(subject.hasFilters()).toBeTruthy()
        })

        it('returns false if the user has not selected any filters', () => {
            subject.selectedFilterOptionState = {
                systemList: [],
                statusList: [],
                priorityList: [],
                targetList: [],
                sensorList: [],
                keywordSearch: ''
            }
            expect(subject.hasFilters()).toBeFalsy()
        })

        it('returns true if the user has input something into search bar', () => {
            subject.selectedFilterOptionState = {
                systemList: [],
                statusList: [],
                priorityList: [],
                targetList: [],
                sensorList: [],
                keywordSearch: 'something'
            }
            expect(subject.hasFilters()).toBeTruthy()
        })

        it('returns false if the user has input an empty string into search bar', () => {
            subject.selectedFilterOptionState = {
                systemList: [],
                statusList: [],
                priorityList: [],
                targetList: [],
                sensorList: [],
                keywordSearch: ''
            }
            expect(subject.hasFilters()).toBeFalsy()
        })
    })

    describe('getFilterState', () => {
        it('should return the record filter highlightState for the specified filter name', () => {
            subject.selectedFilterOptionState = {
                systemList: [{id: 2, value: 'PRISM', system: RecordType.NOMINATION}],
                statusList: [{id: 1, value: 'FANTASTIC!!!', system: RecordType.NOMINATION}],
                priorityList: [{id: 1, value: 'NOM_Working', system: RecordType.RFI}],
                targetList: [],
                sensorList: [],
                keywordSearch: ''
            }
            expect(subject.getFilterState('systemList')).toEqual([{id: 2, value: 'PRISM', system: RecordType.NOMINATION}])
            expect(subject.getFilterState('statusList')).toEqual([{id: 1, value: 'FANTASTIC!!!', system: RecordType.NOMINATION}])
            expect(subject.getFilterState('priorityList')).toEqual([{id: 1, value: 'NOM_Working', system: RecordType.RFI}])
        })
    })

    describe('updateFilter', () => {
        let unfilteredRfis
        let unfilteredNominations

        beforeEach(() => {
            unfilteredRfis = [
                makeRfiListItem({status: 'TO_DO', priority: 'IMMEDIATE'}),
                makeRfiListItem({status: 'TO_DO', priority: 'LOW'}),
                makeRfiListItem({status: 'WORKING', priority: 'ROUTINE'})
            ]

            unfilteredNominations = [
                makeNomCrListItem({status: 'TO_DO', priority: 'IMMEDIATE'}),
                makeNomCrListItem({status: 'DONE', priority: 'Low'})
            ]

            subject.unfilteredRfis = unfilteredRfis
            subject.unfilteredNominations = unfilteredNominations
        })

        it('should update the record filter highlightState and apply the filters', () => {
            let updatedFilters = [
                {id: 1, value: 'WORKING', system: RecordType.RFI},
                {id: 2, value: 'DONE', system: RecordType.NOMINATION}
            ]
            subject.updateFilter('statusList', updatedFilters)

            let filteredRfis = [makeRfiListItem({status: 'WORKING', priority: 'ROUTINE'})]
            let filteredNominations = [makeNomCrListItem({status: 'DONE', priority: 'Low'})]

            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredRfiAction(filteredRfis))
            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredNominationAction(filteredNominations))
            expect(postSpy).toHaveBeenCalledWith('/api/v1/filters/', subject.selectedFilterOptionState)
            expect(getSpy).toHaveBeenCalledWith('/api/v1/filters/')
        })

        it('selecting an RFI priority filter clears the nominations list', () => {
            subject.updateFilter('statusList', [{id: 1, value: 'TO_DO', system: RecordType.RFI}])

            let filteredRfis = [
                makeRfiListItem({status: 'TO_DO', priority: 'IMMEDIATE'}),
                makeRfiListItem({status: 'TO_DO', priority: 'LOW'})
            ]

            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredRfiAction(filteredRfis))
            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredNominationAction([]))
            expect(postSpy).toHaveBeenCalledWith('/api/v1/filters/', subject.selectedFilterOptionState)
            expect(getSpy).toHaveBeenCalledWith('/api/v1/filters/')
        })

        it('selecting a PRISM priority filter clears the RFI list', () => {
            subject.updateFilter('statusList', [{id: 1, value: 'TO_DO', system: RecordType.NOMINATION}])

            let filteredNominations = [makeNomCrListItem({status: 'TO_DO', priority: 'IMMEDIATE'})]

            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredRfiAction([]))
            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredNominationAction(filteredNominations))
            expect(postSpy).toHaveBeenCalledWith('/api/v1/filters/', subject.selectedFilterOptionState)
            expect(getSpy).toHaveBeenCalledWith('/api/v1/filters/')
        })
    })

    describe('updateSearch', () => {
        it('applies selected option filters and search term filter', () => {
            subject.unfilteredRfis = [
                makeRfiListItem({title: 'title1', status: 'TO_DO', priority: 'IMMEDIATE'}),
                makeRfiListItem({title: 'title2', status: 'TO_DO', priority: 'LOW'}),
                makeRfiListItem({title: 'title3', status: 'WORKING', priority: 'ROUTINE'}),
                makeRfiListItem({title: 'no match', status: 'WORKING', priority: 'ROUTINE'})
            ]

            subject.unfilteredNominations = [
                makeNomCrListItem({title: 'title4', status: 'TO_DO', priority: 'IMMEDIATE'}),
            ]

            subject.selectedFilterOptionState = {
                statusList: [{id: 1, value: 'WORKING', system: RecordType.RFI}],
                priorityList: [],
                systemList: [],
                targetList: [],
                sensorList: [],
                keywordSearch: ''
            }
            subject.updateSearchFilter('title')

            let filteredRfis = [makeRfiListItem({title: 'title3', status: 'WORKING', priority: 'ROUTINE'})]

            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredRfiAction(filteredRfis))
            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredNominationAction([]))
        })

        it('applies search filter on multiple fields', () => {
            subject.unfilteredRfis = [
                makeRfiListItem({
                    title: 'title1',
                    status: 'TO_DO',
                    priority: 'IMMEDIATE',
                    justification: 'TEST_JUSTIFICATION1'
                }),
                makeRfiListItem({title: 'title2', status: 'TO_DO', priority: 'LOW', country: 'TEST_COUNTRY1'}),
                makeRfiListItem({title: 'title3', status: 'WORKING', priority: 'ROUTINE', city: 'TEST_CITY1'}),
                makeRfiListItem({title: 'title4', status: 'WORKING', priority: 'ROUTINE', region: 'TEST_REGION1'}),
                makeRfiListItem({title: 'title5', status: 'WORKING', priority: 'ROUTINE', nai: 'TEST_NAI1'}),
                makeRfiListItem({
                    title: 'title6',
                    status: 'WORKING',
                    priority: 'ROUTINE',
                    prevResearch: 'TEST_PREVRESEARCH1'
                }),
                makeRfiListItem({title: 'title7', status: 'WORKING', priority: 'ROUTINE', poc: 'TEST_POC1'}),
                makeRfiListItem({
                    title: 'title8',
                    status: 'WORKING',
                    priority: 'ROUTINE',
                    operation: 'TEST_OPERATION1'
                }),
                makeRfiListItem({title: 'no match', status: 'WORKING', priority: 'ROUTINE', operation: 'XXX'})
            ]

            subject.unfilteredNominations = [
                makeNomCrListItem({id: 'TEST_ID1', title: 'title0', status: 'TO_DO', priority: 'IMMEDIATE'}),
                makeNomCrListItem({
                    title: 'title1',
                    status: 'TO_DO',
                    priority: 'IMMEDIATE',
                    justification: 'TEST_JUSTIFICATION1'
                }),
                makeNomCrListItem({title: 'title2', status: 'TO_DO', priority: 'LOW', country: 'TEST_COUNTRY1'}),
                makeNomCrListItem({title: 'title3', status: 'WORKING', priority: 'ROUTINE', city: 'TEST_CITY1'}),
                makeNomCrListItem({title: 'title4', status: 'WORKING', priority: 'ROUTINE', region: 'TEST_REGION1'}),
                makeNomCrListItem({title: 'title5', status: 'WORKING', priority: 'ROUTINE', nai: 'TEST_NAI1'}),
                makeNomCrListItem({
                    title: 'title6',
                    status: 'WORKING',
                    priority: 'ROUTINE',
                    prevResearch: 'TEST_PREVRESEARCH1'
                }),
                makeNomCrListItem({title: 'title7', status: 'WORKING', priority: 'ROUTINE', poc: 'TEST_POC1'}),
                makeNomCrListItem({
                    title: 'title8',
                    status: 'WORKING',
                    priority: 'ROUTINE',
                    operation: 'TEST_OPERATION1'
                }),
                makeNomCrListItem({title: 'no match', status: 'WORKING', priority: 'ROUTINE', operation: 'XXX'})
            ]

            subject.selectedFilterOptionState = {
                statusList: [],
                priorityList: [],
                systemList: [],
                targetList: [],
                sensorList: [],
                keywordSearch: ''
            }
            subject.updateSearchFilter('TEST')

            let filteredRfis = [
                makeRfiListItem({
                    title: 'title1',
                    status: 'TO_DO',
                    priority: 'IMMEDIATE',
                    justification: 'TEST_JUSTIFICATION1'
                }),
                makeRfiListItem({title: 'title2', status: 'TO_DO', priority: 'LOW', country: 'TEST_COUNTRY1'}),
                makeRfiListItem({title: 'title3', status: 'WORKING', priority: 'ROUTINE', city: 'TEST_CITY1'}),
                makeRfiListItem({title: 'title4', status: 'WORKING', priority: 'ROUTINE', region: 'TEST_REGION1'}),
                makeRfiListItem({title: 'title5', status: 'WORKING', priority: 'ROUTINE', nai: 'TEST_NAI1'}),
                makeRfiListItem({
                    title: 'title6',
                    status: 'WORKING',
                    priority: 'ROUTINE',
                    prevResearch: 'TEST_PREVRESEARCH1'
                }),
                makeRfiListItem({title: 'title7', status: 'WORKING', priority: 'ROUTINE', poc: 'TEST_POC1'}),
                makeRfiListItem({title: 'title8', status: 'WORKING', priority: 'ROUTINE', operation: 'TEST_OPERATION1'})
            ]
            let filteredNoms = [
                makeNomCrListItem({id: 'TEST_ID1', title: 'title0', status: 'TO_DO', priority: 'IMMEDIATE'}),
                makeNomCrListItem({
                    title: 'title1',
                    status: 'TO_DO',
                    priority: 'IMMEDIATE',
                    justification: 'TEST_JUSTIFICATION1'
                }),
                makeNomCrListItem({title: 'title2', status: 'TO_DO', priority: 'LOW', country: 'TEST_COUNTRY1'}),
                makeNomCrListItem({title: 'title3', status: 'WORKING', priority: 'ROUTINE', city: 'TEST_CITY1'}),
                makeNomCrListItem({title: 'title4', status: 'WORKING', priority: 'ROUTINE', region: 'TEST_REGION1'}),
                makeNomCrListItem({title: 'title5', status: 'WORKING', priority: 'ROUTINE', nai: 'TEST_NAI1'}),
                makeNomCrListItem({
                    title: 'title6',
                    status: 'WORKING',
                    priority: 'ROUTINE',
                    prevResearch: 'TEST_PREVRESEARCH1'
                }),
                makeNomCrListItem({title: 'title7', status: 'WORKING', priority: 'ROUTINE', poc: 'TEST_POC1'}),
                makeNomCrListItem({
                    title: 'title8',
                    status: 'WORKING',
                    priority: 'ROUTINE',
                    operation: 'TEST_OPERATION1'
                })
            ]

            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredRfiAction(filteredRfis))
            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredNominationAction(filteredNoms))
        })

        it('searching is case insensitive', () => {
            subject.unfilteredRfis = [
                makeRfiListItem({title: 'Title1', status: 'TO_DO', priority: 'IMMEDIATE'}),
                makeRfiListItem({title: 'TITLE2', status: 'TO_DO', priority: 'LOW'}),
                makeRfiListItem({title: 'TitlE3', status: 'WORKING', priority: 'ROUTINE'}),
                makeRfiListItem({title: 'no match', status: 'WORKING', priority: 'ROUTINE'})
            ]

            subject.unfilteredNominations = [
                makeNomCrListItem({title: 'Title1', status: 'TO_DO', priority: 'IMMEDIATE'}),
                makeNomCrListItem({title: 'TITLE2', status: 'TO_DO', priority: 'LOW'}),
                makeNomCrListItem({title: 'TitlE3', status: 'WORKING', priority: 'ROUTINE'}),
                makeNomCrListItem({title: 'no match', status: 'WORKING', priority: 'ROUTINE'})
            ]

            let filteredRfis = [
                makeRfiListItem({title: 'Title1', status: 'TO_DO', priority: 'IMMEDIATE'}),
                makeRfiListItem({title: 'TITLE2', status: 'TO_DO', priority: 'LOW'}),
                makeRfiListItem({title: 'TitlE3', status: 'WORKING', priority: 'ROUTINE'})
            ]

            let filteredNominations = [
                makeNomCrListItem({title: 'Title1', status: 'TO_DO', priority: 'IMMEDIATE'}),
                makeNomCrListItem({title: 'TITLE2', status: 'TO_DO', priority: 'LOW'}),
                makeNomCrListItem({title: 'TitlE3', status: 'WORKING', priority: 'ROUTINE'})
            ]

            subject.updateSearchFilter('title')
            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredRfiAction(filteredRfis))
            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredNominationAction(filteredNominations))

            subject.updateSearchFilter('TITLE')
            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredRfiAction(filteredRfis))
            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredNominationAction(filteredNominations))
        })

        it('searches by target name', () => {
            subject.unfilteredRfis = [
                makeRfiListItem({title: 'title0'}),
                makeRfiListItem({
                    title: 'title1',
                    targets: [makeRecordTarget({name: 'my less than amazing target name'})]
                }),
                makeRfiListItem({title: 'title2', targets: [makeRecordTarget({name: 'my amazing target name'})]}),
            ]

            subject.unfilteredNominations = [
                makeNomCrListItem({title: 'title4'}),
                makeNomCrListItem({
                    title: 'title4',
                    targets: [makeRecordTarget({name: 'my less than amazing target name'})]
                }),
                makeNomCrListItem({
                    title: 'title4',
                    targets: [makeRecordTarget({name: 'my AMAZING target name with additional info'})]
                }),
            ]

            subject.updateSearchFilter('my amazing target name')

            let filteredRfis = [makeRfiListItem({
                title: 'title2',
                targets: [makeRecordTarget({name: 'my amazing target name'})]
            })]
            let filteredNominations = [makeNomCrListItem({
                title: 'title4',
                targets: [makeRecordTarget({name: 'my AMAZING target name with additional info'})]
            })]

            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredRfiAction(filteredRfis))
            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredNominationAction(filteredNominations))
        })
    })

    describe('store subscriptions', () => {
        it('updates filtered nominations when nominations are received', () => {
            let receivedRfis = [
                makeRfiListItem({status: 'TO_DO', priority: 'IMMEDIATE'}),
                makeRfiListItem({status: 'TO_DO', priority: 'LOW'}),
                makeRfiListItem({status: 'WORKING', priority: 'ROUTINE'})
            ]
            subject.selectedFilterOptionState = {
                systemList: [],
                statusList: [{id: 1, value: 'TO_DO', system: RecordType.RFI}],
                priorityList: [],
                targetList: [],
                sensorList: [],
                keywordSearch: ''
            }

            store.dispatch(new ReceivedRfisAction(receivedRfis))

            let filteredRfis = [
                makeRfiListItem({status: 'TO_DO', priority: 'IMMEDIATE'}),
                makeRfiListItem({status: 'TO_DO', priority: 'LOW'})
            ]
            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredRfiAction(filteredRfis))
        })

        it('updates filtered rfis when rfis are received', () => {
            let receivedNominations = [
                makeNomCrListItem({status: 'TO_DO', priority: 'IMMEDIATE'}),
                makeNomCrListItem({status: 'DONE', priority: 'Low'})
            ]
            subject.selectedFilterOptionState = {
                systemList: [],
                statusList: [{id: 1, value: 'TO_DO', system: RecordType.NOMINATION}],
                priorityList: [],
                targetList: [],
                sensorList: [],
                keywordSearch: ''
            }

            store.dispatch(new ReceivedPrismNominationsAction(receivedNominations))

            let filteredNominations = [makeNomCrListItem({status: 'TO_DO', priority: 'IMMEDIATE'})]
            expect(dispatchSpy).toHaveBeenCalledWith(new UpdateFilteredNominationAction(filteredNominations))
        })
    })
})