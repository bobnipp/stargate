import {ComponentFixture, TestBed} from '@angular/core/testing'

import {FormsModule} from '@angular/forms'
import {RfiService} from '../../services/rfi.service'
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core'
import {Store, StoreModule} from '@ngrx/store'
import {MockRfiService} from '../../../test_helpers/mockRfiService'
import {AppState} from '../../state/appstate'
import {PrismService} from '../../services/prism.service'
import {MockPrismService} from '../../../test_helpers/mockPrismService'
import {ClickOutsideModule} from 'ng-click-outside'
import {makeNomCrListItem, makeRfi, makeRfiListItem} from '../../../test_helpers/utilities'
import {UpdateFilteredNominationAction, UpdateFilteredRfiAction} from '../../state/actions'
import {filteredNominationsReducer, filteredRfisReducer} from '../../state/reducers'
import {RecordType} from "../../models/record.model"
import {SystemListComponent} from "./system-list.component";
import {RecordService} from "../../services/record.service";
import Spy = jasmine.Spy;
import {MockHttpClient} from "../../../test_helpers/mockHttpClient";
import {HttpClient} from "@angular/common/http";

describe('SystemListComponent', () => {
    let component: SystemListComponent
    let fetchRfisSpy: Spy
    let fetchNominationsSpy: Spy
    let mockRfiService: MockRfiService
    let mockPrismService: MockPrismService
    let store: Store<AppState>
    let fixture: ComponentFixture<SystemListComponent>
    let dispatchSpy: Spy

    beforeEach(async () => {
        mockRfiService = new MockRfiService()
        mockPrismService = new MockPrismService()
        const mockHttpClient = new MockHttpClient()

        TestBed.configureTestingModule({
            declarations: [SystemListComponent],
            providers: [
                RecordService,
                {provide: HttpClient, useValue: mockHttpClient},
                {provide: RfiService, useValue: mockRfiService},
                {provide: PrismService, useValue: mockPrismService}
            ],
            imports: [
                FormsModule,
                ClickOutsideModule,
                StoreModule.forRoot({
                    filteredRfis: filteredRfisReducer,
                    filteredNominations: filteredNominationsReducer
                })
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        })

        store = TestBed.get(Store)
        dispatchSpy = spyOn(store, 'dispatch').and.callThrough()
        fetchRfisSpy = spyOn(mockRfiService, 'fetchRfis').and.callThrough()
        fetchNominationsSpy = spyOn(mockPrismService, 'fetchNominations').and.callThrough()
        fixture = TestBed.createComponent(SystemListComponent)
        component = fixture.componentInstance
    })

    describe('onLoad', () => {
        it('fetches rfis and receives filtered rfis', () => {
            const expectedRfis = [
                makeRfiListItem({id: 'foo'}),
                makeRfiListItem({id: 'buzz'}),
            ]

            fetchRfisSpy.and.callFake(() => {
                store.dispatch(new UpdateFilteredRfiAction(expectedRfis))
            })

            component.listType = RecordType.RFI
            component.ngOnInit()

            expect(fetchRfisSpy).toHaveBeenCalled()
            expect(component.systemList).toEqual(expectedRfis)
        })

        it('fetches nominations and receives filtered nominations', () => {
            const expectedNominations = [
                makeNomCrListItem({id: 'foo'}),
                makeNomCrListItem({id: 'buzz'})
            ]

            fetchNominationsSpy.and.callFake(() => {
                store.dispatch(new UpdateFilteredNominationAction(expectedNominations))
            })

            component.listType = RecordType.NOMINATION
            component.ngOnInit()

            expect(fetchNominationsSpy).toHaveBeenCalled()
            expect(component.systemList).toEqual(expectedNominations)
        })
    })

    it('recognizes active links', () => {
        const rfi = makeRfi({
            id: 'rfi1',
            links: [
                {
                    record1Id: 'rfi1',
                    record2Id: 'rfi2',
                    record1System: 'IMM',
                    record2System: 'IMM',
                    title: '',
                    status: ''
                },
                {
                    record1Id: 'rfi3',
                    record2Id: 'rfi1',
                    record1System: 'IMM',
                    record2System: 'IMM',
                    title: '',
                    status: ''
                },
                {
                    record1Id: 'rfi1',
                    record2Id: 'nom1',
                    record1System: 'IMM',
                    record2System: 'PRISM',
                    title: '',
                    status: ''
                },
                {
                    record1Id: 'nom2',
                    record2Id: 'rfi1',
                    record1System: 'PRISM',
                    record2System: 'IMM',
                    title: '',
                    status: ''
                }
            ]
        })

        component.linkRecords = [rfi]
        expect(component.isActiveLink(rfi.id)).toBeTruthy()
        expect(component.isActiveLink('XXYYZZ')).toBeFalsy()
    })

    it ('calls refresh', () => {
        const refreshSpy = spyOn(component.refreshList, 'emit')
        component.listType = RecordType.RFI
        component.refresh()
        expect(refreshSpy).toHaveBeenCalledWith(RecordType.RFI)
    })

    it ('sorts by title and date, ascending and descending', () => {
        const recordItem1 = makeRfiListItem({id: '1', title: 'title b', createdOn: '2019-02-15 17:56:10'})
        const recordItem2 = makeRfiListItem({id: '2', title: 'title a', createdOn: '2019-02-14 17:50:15'})
        const recordItem3 = makeRfiListItem({id: '3', title: 'title c', createdOn: '2019-02-14 17:50:10'})
        component.systemList = [recordItem1, recordItem2, recordItem3]

        expect(component.sortOptions).toEqual([{name: 'Date', field: 'createdOn'}, {name: 'Title', field: 'title'}])
        expect(component.selectedSort).toEqual({name: 'Date', field: 'createdOn'})
        expect(component.isDescendingSort).toEqual(true)

        component.updateSelectedSort('Title')
        expect(component.selectedSort).toEqual({name: 'Title', field: 'title'})
        expect(component.systemList).toEqual([recordItem3, recordItem1, recordItem2])

        component.toggleSortDirection()
        expect(component.isDescendingSort).toEqual(false)
        expect(component.selectedSort).toEqual({name: 'Title', field: 'title'})
        expect(component.systemList).toEqual([recordItem2, recordItem1, recordItem3])

        component.updateSelectedSort('Date')
        expect(component.isDescendingSort).toEqual(false)
        expect(component.selectedSort).toEqual({name: 'Date', field: 'createdOn'})
        expect(component.systemList).toEqual([recordItem3, recordItem2, recordItem1])

        component.toggleSortDirection()
        expect(component.isDescendingSort).toEqual(true)
        expect(component.selectedSort).toEqual({name: 'Date', field: 'createdOn'})
        expect(component.systemList).toEqual([recordItem1, recordItem2, recordItem3])
    })
})
